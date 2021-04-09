package com.data.covid19.services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.data.covid19.bo.SummaryByCountry;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class SummaryByCountryService {
	 public static final String COL_NAME="SummaryByCountry";  
	 public String savePatientDetails(SummaryByCountry summaryByCountry) throws InterruptedException, ExecutionException {  
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
	 public String deleteSummaryByCountry(String country) {  
	 Firestore dbFirestore = FirestoreClient.getFirestore();  
	 ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(country).delete();  
	 return "Document with SummaryByCountry ID "+country+" has been deleted";  
	 }}