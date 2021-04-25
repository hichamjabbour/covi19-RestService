package com.data.covid19.services;

import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.data.covid19.bo.SummaryByCountry;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class SummaryByCountryService {
	 public static final String COL_NAME="SummaryByCountry"; 
	 private final Logger LOG = LogManager.getLogger(getClass());


	 public String saveSummaryByCountryDetails(SummaryByCountry summaryByCountry) throws InterruptedException, ExecutionException {  
	 Firestore dbFirestore = FirestoreClient.getFirestore();  
	 ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(summaryByCountry.getCountry()).set(summaryByCountry);  
	 return collectionsApiFuture.get().getUpdateTime().toString();  
	 }  
	 public SummaryByCountry getSummaryByCountryDetails(String name) throws InterruptedException, ExecutionException {
	 Firestore dbFirestore = FirestoreClient.getFirestore();  	 
	 DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);  
	 ApiFuture<DocumentSnapshot> future = documentReference.get();  
	 DocumentSnapshot document = future.get();  
	 SummaryByCountry summaryByCountry = null;  
	 if(document.exists()) {  
	 summaryByCountry = document.toObject(SummaryByCountry.class);  
	 return summaryByCountry;  
	 }else {  
	 return null;  
	 }  
	 }  
	 public String updateSummaryByCountry(SummaryByCountry summaryByCountry) throws InterruptedException, ExecutionException {  
	 Firestore dbFirestore = FirestoreClient.getFirestore();  
	 ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(summaryByCountry.getCountry()).set(summaryByCountry);  
	 return collectionsApiFuture.get().getUpdateTime().toString();  
	 }  
	 @SuppressWarnings("deprecation")
	public String deleteSummaryByCountry(String country) throws InterruptedException, ExecutionException {  
	 Firestore dbFirestore = FirestoreClient.getFirestore();  
	 DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(country);
	 ApiFuture<DocumentSnapshot> future = documentReference.get();  
	 DocumentSnapshot document = future.get();
	 if(document != null)
	 {
		 ApiFuture<WriteResult> collectionsApiFuture = documentReference.delete();
		 
		 ApiFutures.addCallback(
				 collectionsApiFuture,
			        new ApiFutureCallback<WriteResult>() {
			          @Override
			          public void onFailure(Throwable t) {
			            LOG.error("Error deleting country =" + country, t);
			          }

			          @Override
			          public void onSuccess(WriteResult result) {
			            LOG.debug("Deleted successfully country = " + country + "with time = " + result.getUpdateTime());
			          }
				 },MoreExecutors.directExecutor());
		 
		 return collectionsApiFuture.get().getUpdateTime().toString(); 
		 
	 }
	 
    return null;  
	 }
   }