/*
joystickPolling.ino
*/
int buttonPin = 7 ;//interrupt 4
int ledPin = 20; //A2 


boolean isPressed = false;
boolean prevState = false;
int n = 0;

void setup() {                
  pinMode(buttonPin, INPUT);
  digitalWrite(buttonPin, HIGH);  //attach pull up => unressed = 1
  
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW); //init
}
// the loop routine runs over and over again forever:
void loop() {
   int v = digitalRead(buttonPin);   
   isPressed = ( v == 0 ) ; 
   if( prevState != isPressed ){
          n++;        
          Serial.println("arduino(button,"+String(n) + ", value(" + String(isPressed) +"))") ;
          prevState = isPressed;
    }
    if( isPressed) digitalWrite(ledPin, HIGH);
    else digitalWrite(ledPin, LOW);
    delay(10);               // wait for 0.01 second
}
