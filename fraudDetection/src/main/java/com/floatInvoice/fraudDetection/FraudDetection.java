package com.floatInvoice.fraudDetection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import com.floatinvoice.messages.FraudInvoiceDtls;

public class FraudDetection {

	/*private Map<Integer,Double> actualProportion=new HashMap<Integer, Double>();
	private final String appName="FraudDetector";
	private final String masterAddress="spark://localhost:7077";
	private final String jarPath="D:\\FraudDetection.jar";
	private SparkConf conf;
	private JavaSparkContext context;


	public FraudDetection() {

		conf = new SparkConf().setAppName(appName).setMaster("spark://localhost:7077");		
		context = new JavaSparkContext(conf);
		//context.addJar(jarPath);

	}

	private static final FlatMapFunction<FraudInvoiceDtls, String> EXTRACTOR =
			new FlatMapFunction<FraudInvoiceDtls, String>() {

		public Iterable<String> call(FraudInvoiceDtls invoiceDtls) throws Exception {
			//String arg[]=s.split(",",-1);
			List<String> list =new ArrayList<String>();
			Double PurchaseAmount = invoiceDtls.getAmount();
			//Benfords Law
			list.add(PurchaseAmount.toString().substring(0,1));
			//Purchase Order Missing
			if(invoiceDtls.getInvoicePONo().equals(""))
				list.add("PurchaseOrderMissing");				
			//Rounded Payment amounts 
			if((PurchaseAmount == Math.floor(PurchaseAmount)))
				list.add("RoundedValue");					
			//Test for Even Amounts
			if((PurchaseAmount%1000d)==0)
				list.add("EvenAmount");			
			//Length in days between Invoice and payment dates
			Calendar invoiceDateCal = new GregorianCalendar();
			Calendar paymentDateCal = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date invoiceDate = sdf.parse(String.valueOf(invoiceDtls.getStartDt()));
			invoiceDateCal.setTime(invoiceDate);
			Date paymentDate = sdf.parse(String.valueOf(invoiceDtls.getPaidDt()));
			paymentDateCal.setTime(paymentDate);

			int days=(int) ((paymentDate.getTime() - invoiceDate.getTime()) / (1000 * 60 * 60 * 24));
			if(days > 30)
				list.add("DaysToPaymentExceedThreshold");

			if(paymentDateCal.get(Calendar.DAY_OF_WEEK)==7)
				list.add("PaymentMadeOnSaturday");
			else if(paymentDateCal.get(Calendar.DAY_OF_WEEK)==1)
				list.add("PaymentMadeOnSunday");
			return list;
		}
	};

	private static final PairFunction<String, String, Integer> MAPPER =
			new PairFunction<String, String, Integer>() {

		public Tuple2<String, Integer> call(String s) throws Exception {
			return new Tuple2<String, Integer>(s, 1);
		}

	};

	private static final Function2<Integer, Integer, Integer> REDUCER =
			new Function2<Integer, Integer, Integer>() {

		public Integer call(Integer a, Integer b) throws Exception {
			return a + b;
		}
	};

	private static final Function<FraudInvoiceDtls, String> GROUP_SSS = new Function<FraudInvoiceDtls, String>() {

		public String call(FraudInvoiceDtls invoiceDtls) throws Exception {
			//String arg[]=arg0.split(",",-1);

			//return arg[0]+","+arg[1]+","+arg[3]+","+arg[4]+","+arg[5];
			return invoiceDtls.getInvoiceNo() + "," + invoiceDtls.getStartDt() + "," 
			+ invoiceDtls.getBuyerId() + "," + invoiceDtls.getSupplierId() + "," + invoiceDtls.getAmount();
					
		}

	};

	private static final Function<FraudInvoiceDtls, String> GROUP_SSD =new Function<FraudInvoiceDtls, String>() {

		public String call(FraudInvoiceDtls invoiceDtls) throws Exception {
			//String arg[]=arg0.split(",",-1);

			return invoiceDtls.getInvoiceNo() + "," + invoiceDtls.getStartDt() + "," 
			+ invoiceDtls.getBuyerId() + "," + invoiceDtls.getAmount();
		}
	};
	
	private static final Function<Tuple2<String, Iterable<FraudInvoiceDtls>>, Boolean> FILTER_DUPLICATES = 
			new Function<Tuple2<String,Iterable<FraudInvoiceDtls>>, Boolean>() {

				public Boolean call(Tuple2<String, Iterable<FraudInvoiceDtls>> arg)
						throws Exception {
					Iterator<FraudInvoiceDtls> itr= arg._2.iterator();
					itr.next();					
					if(itr.hasNext())											
						return true;					
					else 
						return false;
				}
	};   
	
	private static final FlatMapFunction<Iterable<FraudInvoiceDtls>, FraudInvoiceDtls> IterableToList=
			 new FlatMapFunction<Iterable<FraudInvoiceDtls>, FraudInvoiceDtls>() {

				@Override
				public Iterable<FraudInvoiceDtls> call(Iterable<FraudInvoiceDtls> arg0) throws Exception {
					
					return Lists.newArrayList(arg0);
				}

	};

	public List<FraudTestResults> doFraudTests (List<FraudInvoiceDtls> list) {

		
		List<FraudTestResults> results = new ArrayList<>();
	//	InvoicePuller invoicePuller=new InvoicePuller(path);		
		JavaRDD<FraudInvoiceDtls> file =context.parallelize(list);		

		JavaRDD<String> words = file.flatMap(EXTRACTOR);		
		JavaPairRDD<String, Integer> pairs = words.mapToPair(MAPPER);
		JavaPairRDD<String, Integer> counter = pairs.reduceByKey(REDUCER);
		
		//result.setDuplicateSSS(file.groupBy(GROUP_SSS).filter(FILTER_DUPLICATES).values().flatMap(IterableToList).collect());
		//result.setDuplicateSSD(file.groupBy(GROUP_SSD).filter(FILTER_DUPLICATES).values().flatMap(IterableToList).collect());          

		double totalCount=(double) file.count();

		for (Map.Entry<String, Integer> entry : counter.collectAsMap().entrySet())
		{
			Result result = new Result();
			FraudTestResults ftr = new FraudTestResults();
			results.add(ftr);
			
			try
			{
				actualProportion.put(Integer.parseInt(entry.getKey()), ((double)entry.getValue()/totalCount)*100d);
			}
			catch(NumberFormatException e)
			{
				if(entry.getKey().equals("PurchaseOrderMissing"))
					result.setPurchaseOrderMissing(entry.getValue());
				else if(entry.getKey().equals("RoundedValue"))
					result.setRoundedValues(entry.getValue());
				else if(entry.getKey().equals("EvenAmount"))
					result.setEvenAmounts(entry.getValue());
				else if(entry.getKey().equals("DaysToPaymentExceedThreshold"))
					result.setDaysToPaymentExceedThreshold(entry.getValue());
				else if(entry.getKey().equals("PaymentMadeOnSaturday"))
					result.setPaymentMadeOnSaturday(entry.getValue());
				else if(entry.getKey().equals("PaymentMadeOnSunday"))
					result.setPaymentMadeOnSunday(entry.getValue());
			}

			result.setBenfordsLawResult(actualProportion);
		
		}

		return null;
	}*/
	private static final long serialVersionUID = 1L;

	private static final String appName="FraudDetection";

	private static final String masterAddress="spark://localhost:7077";

	private static SparkConf conf;

	private static JavaSparkContext context;	

	static{
		/*conf = new SparkConf().setAppName(appName).setMaster(masterAddress);		
		conf.set("spark.driver.allowMultipleContexts", "true");
		context = new JavaSparkContext(conf);
*/	}
	
	public FraudDetection() {

	}

	private static Function<FraudInvoiceDtls, FraudTestResults> FRAUD_TEST = new Function<FraudInvoiceDtls,FraudTestResults>() {

		@Override
		public FraudTestResults call(FraudInvoiceDtls fraudInvoiceDtls) throws Exception {
			
			FraudTestResults results = new FraudTestResults();
			results.setInvoiceDtls(fraudInvoiceDtls);
			List<Integer> fraudTestIds = new ArrayList<>();
			results.setFraudTestIds(fraudTestIds);
			
			//Test to check if Purchase Order is missing
			if(fraudInvoiceDtls.getInvoicePONo().trim().equals("")|| fraudInvoiceDtls.getInvoicePONo().equals(null))
				fraudTestIds.add(FraudDetectionTestNameEnums.PurchaseOrder.getCode());
			//Test for Rounded Payment amounts 
			if((fraudInvoiceDtls.getAmount() == Math.floor(fraudInvoiceDtls.getAmount())))
				fraudTestIds.add(FraudDetectionTestNameEnums.RoundedAmts.getCode());
			//Test for Even Amounts
			if( (fraudInvoiceDtls.getAmount() % 1000d )==0)
				fraudTestIds.add(FraudDetectionTestNameEnums.EvenAmts.getCode());
			
			//Test on Invoice and payment dates
			Calendar startDtCal = new GregorianCalendar();
			Calendar paidDtCal = new GregorianCalendar();

			Date startDt = fraudInvoiceDtls.getStartDt();
			Date paidDt = fraudInvoiceDtls.getPaidDt();
			
			if ( startDt != null && paidDt != null){
				startDtCal.setTime(startDt);
				paidDtCal.setTime(paidDt);
				int days=(int) ((paidDt.getTime() - startDt.getTime()) / (1000 * 60 * 60 * 24));
				if(days > 30)
					fraudTestIds.add(FraudDetectionTestNameEnums.LatePayments.getCode());
				if( paidDtCal != null && 
						   (paidDtCal.get(Calendar.DAY_OF_WEEK)== 7 || 
							paidDtCal.get(Calendar.DAY_OF_WEEK)== 1) ){
						fraudTestIds.add(FraudDetectionTestNameEnums.WeekendPayments.getCode());
				}
			}
			
			return results;
		}
	};

	public List<FraudTestResults> doFraudTests (List<FraudInvoiceDtls> fraudInvoiceList ) {

		JavaRDD<FraudInvoiceDtls> fraudInvoices = context.parallelize(fraudInvoiceList);

		JavaRDD<FraudTestResults> fraudInvoicesResult = fraudInvoices.map(FRAUD_TEST);

		return fraudInvoicesResult.collect();
	}
}