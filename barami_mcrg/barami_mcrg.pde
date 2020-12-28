import java.awt.Robot;
import processing.sound.*;
SoundFile file;

Robot robot;

ArrayList<Note> note;

PImage note1,note2;

float l,m;
int dX,dY,mX,mY,pmX,pmY;
int count,sec,frame;
int start;

float sizeX, sizeY;

color c = color(0);

void setup() {
  fullScreen(P2D);
  note = new ArrayList<Note>();
  note1 = loadImage("note1.png");
  note2 = loadImage("note2.png");
  file = new SoundFile(this, "awesomeness.wav");
  try {
    robot = new Robot();
  } 
  catch (Throwable e) {
  }
  
  imageMode(CENTER);
  delay(500);
  frameRate(1000);
  
  sizeX=width/5;
  sizeY=height/10;
  
  mX=height/3;
  mY=height/2;
  pmX=height/3;
  pmY=height/2;
  robot.mouseMove(height/3,height/2);
  
  noCursor();
  
}

void draw() {
  
  background(150);
  
  fill(255);
  arc(100,100,100,150,0,PI,PIE);
  if(mousePressed&&mouseButton==RIGHT) fill(0); else fill(255);
  arc(100,100,100,100,1.5*PI,2*PI,PIE);
  if(mousePressed&&mouseButton==LEFT) fill(0); else fill(255);
  arc(100,100,100,100,PI,1.5*PI,PIE);
  
  fill(c);
  ellipse(200,100,50,50);
  
  fill(255);
  ellipse(height/3,height/2,height/4,height/4);
  m=dist(mouseX,mouseY,height/3,height/2);
  pmX=mX;
  pmY=mY;
  if(m>height/8){
    robot.mouseMove(int(height/3+(mouseX-height/3)/m*height/8.1),int(height/2+(mouseY-height/2)/m*height/8.1));
    mX=int(height/3+(mouseX-height/3)/m*height/8.1);
    mY=int(height/2+(mouseY-height/2)/m*height/8.1);
  }
  else{
    mX=mouseX;
    mY=mouseY;
  }
  fill(0);
  ellipse(mX,mY,height/50,height/50);
  
  mouse_set();
  
  for(int i=0;i<note.size();i++){
    if(millis()>note.get(i).t+500) note.remove(i);
    else if(note.get(i).check()) note.remove(i);
    else if(millis()<note.get(i).t-1500) break;
    else note.get(i).display();
  }
  
  if(sec!=second()){
    frame=count;
    count=0;
    sec=second();
  }
  count++;
  fill(0);
  text("frameRate_"+frame,20,35);
  
  
}

void mousePressed() {
  if(mouseButton==RIGHT){
    if (file.isPlaying()) {
      while(note.size()!=0) note.remove(0);
      file.pause();
      c = color(255,0,0);
    }
    else {
      while(note.size()!=0) note.remove(0);
      file.jump(0);
      start = millis()+250;
      file_awesomeness();
      c = color(0,255,0);
    }
  }
}
