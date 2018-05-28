/*
  Blink
  Turns on an LED on for one second, then off for one second, repeatedly.
 
  This example code is in the public domain.
 */
 
// Pin 13 has an LED connected on most Arduino boards.
// give it a name:
int led = 13;
int n = 0;
char c = 'A';

// the setup routine runs once when you press reset:
void setup() {                
  // initialize the digital pin as an output.
  Serial.begin(9600);
  pinMode(led, OUTPUT);     
}

// the loop routine runs over and over again forever:
void loop() {
  
  if(Serial.available()) {
    //c = Serial.read();
    digitalWrite(led, HIGH); 
    //Serial.print(c);
  }
  else 
    digitalWrite(led, LOW);
    */
     
    Serial.print(c++);
 
    if(c > 'Z') c = 'A';
  delay(20);
}
