/*
=======================================
blsInterruptUnoNormalButton.ino
ARDUINO UNO
pin 3 maps to interrupt 1
pin 2 is interrupt 0 
=======================================
PIN
We use just the sw pin of a button 
Pin 13 has an LED connected on most Arduino boards. 
-----------------------------
We use functions to give more structure and readability to the code
-----------------------------
*/
int joyStickSw   = 3;
int normalButton = 2;   
int led = 13;  
/* 
-----------------------------
PROGRAM VARIABLES  
-----------------------------
*/
boolean toggle = true;
  
void initLed(){
   pinMode(led, OUTPUT);  
}
void initButton(){
  //pinMode(joyStickSw, INPUT); 
  pinMode(normalButton, INPUT);
  //digitalWrite(joyStickSw, HIGH);
  attachInterrupt(0, buttonInterruptHandler, CHANGE); // RISING CHANGE FOLLING LOW
  //attachInterrupt(1, joystickInterruptHandler, CHANGE); // RISING CHANGE FOLLING LOW
  Serial.println("Initialized buttons");
}
/*
-----------------------------
INTERRUPT HANDLER function
-----------------------------
*/ 
void buttonInterruptHandler(){
  boolean ok = debouncing(); 
  Serial.println("Clicked");
  if( ok ){
    int sw  = digitalRead(normalButton);
    if( sw == 1 ) toggle = !toggle;
    Serial.println(   "interrupt sw=" + String(sw));
  }
}
/*
void joystickInterruptHandler(){
  boolean ok = debouncing(); 
  if( ok ){
    int sw  = digitalRead(joyStickSw);
    if( sw == 1 ) toggle = !toggle;
    Serial.println(   "interrupt sw=" + String(sw));
  }
}
*/

boolean debouncing(){
static unsigned long lastInterruptTime = 0;
static int  lastSw = 0;
unsigned long interruptTime = millis(); 
int sw = digitalRead(joyStickSw);
  if( (interruptTime - lastInterruptTime) > 100 ){
    if( (sw != lastSw) ) lastSw = sw;
    return true;
  }
  lastInterruptTime = interruptTime;
  return false;
}
/*
-----------------------------
MANDATORY function
-----------------------------
*/
void setup(){  
   Serial.begin(9600); 
   initLed();
   initButton();
}
/*
-----------------------------
USER defined function
-----------------------------
*/
void blinkTheLed(){
   digitalWrite(led, HIGH); 
   delay(500);
   digitalWrite(led, LOW);  
   delay(3000);
   int val = digitalRead(normalButton);
   Serial.println("Value: " + String(val) + "...");
}
void loop(){
  if( toggle ) blinkTheLed();
}
