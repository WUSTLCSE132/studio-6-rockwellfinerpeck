const int buttonPin = 2;
int buttonCount = 0;
unsigned long currentTime;

unsigned long prevTime;
unsigned long nextBounceTime = 0;
const long debounceDelay = 100;

const long deltaTime = 1000;
unsigned long nextTime = 0;

void buttonPressed() {
  if (currentTime >= nextBounceTime){
      Serial.print("Interrupt ");
      ++buttonCount;
      nextBounceTime = currentTime + debounceDelay;
      Serial.println(buttonCount);
  }
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, FALLING);
  Serial.begin(9600);
}

void loop() {
  currentTime = millis();
  if(currentTime >= nextTime){
    Serial.println("Second");
    nextTime += deltaTime;
  }
}
