/*
=======================================
blsInterruptUno.ino
ARDUINO UNO
pin 3 maps to interrupt 1
pin 2 is interrupt 0 
=======================================
PIN
We use just the sw pin of a joystickbutton 
Pin 13 has an LED connected on most Arduino boards. 
--------------------------------------------------------------------
We use functions to give more structure and readability to the code.
Nevertheless, this is quite a "naive" program that does not reflect
the logic architecture of our system. 
TODO: use cpp classes to define the button, the led and the contrl
--------------------------------------------------------------------
*/
int buttonPin   = 3;   
int ledPin = 13;  
boolean blinking = false;
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
INITIALIZATION
-----------------------------
*/ 
void initLed(){
   pinMode(ledPin, OUTPUT);  
}
void initButton(){   
  pinMode(buttonPin, INPUT);
  digitalWrite(buttonPin, HIGH);   //dynamiccaly attach pull up => unressed = 1
  attachInterrupt(1, buttonInterruptHandler, CHANGE); // RISING CHANGE FOLLING LOW
  Serial.println("arduino(button(i),v(0))"); 
}
/*
-----------------------------
INTERRUPT HANDLER function
-----------------------------
*/ 
void buttonInterruptHandler(){
  if( debouncing() ){
    int sw  = digitalRead(buttonPin);
    doTheWork(sw);
  }
}
//Sends arduino(button(B),v(X)) ; B = i, X = 0 | 1
void doTheWork( int sw ){
   Serial.println("arduino(button(i),v(" + String(sw) +"))") ;
   if( sw == 1 ){
     blinking = !blinking;
   } 
}
boolean debouncing(){
static unsigned long lastInterruptTime = 0;
static int  lastSw = 0;
unsigned long interruptTime = millis(); 
int sw = digitalRead(buttonPin);
  if( (interruptTime - lastInterruptTime) > 100 ){
    if( sw != lastSw ){
      lastSw = sw;
      return true;
    }else return false;
  }
  lastInterruptTime = interruptTime;
  return false;
}

/*
-----------------------------
USER defined function
-----------------------------
*/
void blinkTheLed(){
   digitalWrite(ledPin, HIGH); 
   delay(500);
   digitalWrite(ledPin, LOW);  
   delay(500);
}
/*
-----------------------------
loop
-----------------------------
*/
void loop(){
  if( blinking ) blinkTheLed();
}
