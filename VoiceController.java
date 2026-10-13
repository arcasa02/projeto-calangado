package com.projeto.calangado;
import android.speech.SpeechRecognizer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
VoiceController implements RecognitionListener {
private SpeechRecognizer speechRecognizer;
private Context context;
public VoiceController(Context context) { this.context = context; }
public void initSpeechRecognizer() {
speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
speechRecognizer.setRecognitionListener(this);
@Override public void onReadyForSpeech(Bundle params) {
@Override public void onResults(Bundle results) {
ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
if (matches != null && !matches.isEmpty() && matches.get(0).equals("abrir app")) {
Intent intent = new Intent(context, MainActivity.class);
context.startActivity(Intent); }
@Override public void onError (int error) { 

