/*
joystickInterrupt.ino
/*
ARDUINO PRO MICRO
pin 3 maps to interrupt 0,  SCL
pin 2 is interrupt 1,       SDA
pin 0 is interrupt 2,       RX
pin 1 is interrupt 3,       TX
pin 7 is interrupt 4
*/

/*
ARDUINO UNO
pin 3 maps to interrupt 1, 
pin 2 is interrupt 0, 
*/
//#include <MsTimer2.h>


//int joyStickGnd = A1; //pin 0 is interrupt 3
//int joyStickVcc5 = A2;
int joyStickVrx = A9; 
int joyStickVry = A8;  
int joyStickSw = 7;   
 
// Pin 13 has an LED connected on most Arduino boards. 
int led = A2; //13;
long count = 0;  
boolean isOn = false;

void setup()
{
   Serial.begin(9600); 
   pinMode(led, OUTPUT);  
   //pinMode(joyStickGnd, OUTPUT); 
   //pinMode(joyStickVcc5, OUTPUT); 
   pinMode(joyStickVrx, INPUT); 
   pinMode(joyStickVry, INPUT); 
   pinMode(joyStickSw, INPUT); 
   //digitalWrite(joyStickGnd,LOW) ;
   //digitalWrite(joyStickVcc5,HIGH) ; 
   digitalWrite(joyStickSw, HIGH);
   digitalWrite(led, LOW);
   attachInterrupt(4, joystickInterruptHandler, CHANGE); // RISING CHANGE FOLLING LOW
}

boolean debouncing(){
static unsigned long lastInterruptTime = 0;
static int  lastSw = 0;
unsigned long interruptTime = millis(); 

  if( (interruptTime - lastInterruptTime) > 100 ){
    lastInterruptTime = interruptTime;
    return true;
  }
  return false;
}
 
void joystickInterruptHandler(){
  boolean ok = debouncing(); 
  if( ok ){
    int vrx = analogRead(joyStickVrx) - 520;
    int vry = analogRead(joyStickVry) - 520 ;
    int sw  = digitalRead(joyStickSw);
     Serial.println(    
    "arduino(joystick, vrx( " + String(vrx) + "),vry(" + String(vry) + "), sw(" + String(sw) +")");
    digitalWrite(led, isOn); 
    if( sw == 1 ) isOn = !isOn;
  }
 
}
 

void loop()
{
    //digitalWrite(led, HIGH); 
    int vrx = analogRead(joyStickVrx) - 520;
    int vry = analogRead(joyStickVry) - 520 ;
    int sw  = digitalRead(joyStickSw);
    //Serial.println(   "vrx= " + String(vrx) + " vry=" + String(vry) + " sw=" + String(sw));
    delay(1000);
    //digitalWrite(led, LOW);  
}
