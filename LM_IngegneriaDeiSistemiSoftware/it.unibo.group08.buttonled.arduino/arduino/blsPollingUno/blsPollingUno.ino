/*
=======================================
blsPollingUno.ino
ARDUINO UNO
pin 3 maps to interrupt 1
pin 2 is interrupt 0 
=======================================
PIN
We use just the sw pin of a joystuck button 
Pin 13 has an LED connected on most Arduino boards. 
--------------------------------------------------------------------
We use functions to give more structure and readability to the code.
Nevertheless, this is quite a "naive" program that does not reflect
the logic architecture of our system. 
TODO: use cpp classes to define the button, the led and the contrl
--------------------------------------------------------------------
*/
int buttonPin = 3 ;        //joystick
int ledPin   = 13;  
int buttonNormalPin = 2 ;  //normal button
boolean isPressed = false;
boolean isOn = false;
 
/*
-----------------------------
MANDATORY function
-----------------------------
*/
void setup() {    
  Serial.begin(9600);   
  
  pinMode(buttonPin, INPUT);
  pinMode(buttonNormalPin, INPUT);
  digitalWrite(buttonPin, HIGH);        //dynamiccaly attach pull up => unressed = 1  
  digitalWrite(buttonNormalPin, HIGH);  //dynamiccaly attach pull up => unressed = 1
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);  
 }
/*
-----------------------------
USER defined function
-----------------------------
*/
//Sends arduino(button(B),v(X)) ; B = p, X = 0 | 1
void doTheWork(boolean isPressed){
 
    if( isOn && ! isPressed ){//CHANGE STATE
        isOn = false;
        Serial.println("arduino(button(p),v(" + String(isPressed) +"))") ;
        digitalWrite(ledPin, LOW);
      }else if(  ! isOn && isPressed ){ //CHANGE STATE
       Serial.println("arduino(button(p),v(" + String(isPressed) +"))") ;
       isOn = true;
       digitalWrite(ledPin, HIGH);
      }
}
/*
-----------------------------
loop
-----------------------------
*/
void loop() {
   int v   = digitalRead(buttonPin);          //NO BLOCKING
   int v1  = digitalRead(buttonNormalPin);    //NO BLOCKING
   boolean isPressed = ( v == 0 || v1 == 0 ) ; 
   doTheWork(isPressed);
   delay(50);               // wait for 0.05 second
}
