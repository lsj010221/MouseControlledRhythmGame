import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.Robot; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class barami_mcrg extends PApplet {



SoundFile file;

Robot robot;

ArrayList<Note> note;

PImage note1,note2;

float l,m;
int dX,dY,mX,mY,pmX,pmY;
int count,sec,frame;
int start;

float sizeX, sizeY;

int c = color(0);

public void setup() {
  
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

public void draw() {
  
  background(150);
  
  fill(255);
  arc(100,100,100,150,0,PI,PIE);
  if(mousePressed&&mouseButton==RIGHT) fill(0); else fill(255);
  arc(100,100,100,100,1.5f*PI,2*PI,PIE);
  if(mousePressed&&mouseButton==LEFT) fill(0); else fill(255);
  arc(100,100,100,100,PI,1.5f*PI,PIE);
  
  fill(c);
  ellipse(200,100,50,50);
  
  fill(255);
  ellipse(height/3,height/2,height/4,height/4);
  m=dist(mouseX,mouseY,height/3,height/2);
  pmX=mX;
  pmY=mY;
  if(m>height/8){
    robot.mouseMove(PApplet.parseInt(height/3+(mouseX-height/3)/m*height/8.1f),PApplet.parseInt(height/2+(mouseY-height/2)/m*height/8.1f));
    mX=PApplet.parseInt(height/3+(mouseX-height/3)/m*height/8.1f);
    mY=PApplet.parseInt(height/2+(mouseY-height/2)/m*height/8.1f);
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

public void mousePressed() {
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
public void file_awesomeness(){
  note.add(new Note(3500,0.625f,true));
  note.add(new Note(3750,-0.375f,false));
  note.add(new Note(4000,0.625f,true));
  note.add(new Note(4250,-0.375f,false));
  note.add(new Note(4750,0.625f,true));
  note.add(new Note(5500,0.75f,true));
  note.add(new Note(5750,-0.25f,false));
  note.add(new Note(6000,0.75f,true));
  note.add(new Note(6250,-0.25f,false));
  note.add(new Note(6750,0.75f,true));
  note.add(new Note(7500,0.875f,true));
  note.add(new Note(7750,-0.125f,false));
  note.add(new Note(8000,0.875f,true));
  note.add(new Note(8250,-0.125f,false));
  note.add(new Note(8750,0.875f,true));
  note.add(new Note(9500,1,true));
  note.add(new Note(9750,0,false));
  note.add(new Note(10000,1,false));
  note.add(new Note(10250,0,true));
  note.add(new Note(11500,0.625f,true));
  note.add(new Note(11750,-0.375f,false));
  note.add(new Note(12000,0.625f,true));
  note.add(new Note(12250,-0.375f,false));
  note.add(new Note(12750,0.625f,true));
  note.add(new Note(13500,0.75f,true));
  note.add(new Note(13750,-0.25f,false));
  note.add(new Note(14000,0.75f,true));
  note.add(new Note(14250,-0.25f,false));
  note.add(new Note(14750,0.75f,true));
  note.add(new Note(15500,0.875f,true));
  note.add(new Note(15750,-0.125f,false));
  note.add(new Note(16000,0.875f,true));
  note.add(new Note(16250,-0.125f,false));
  note.add(new Note(16750,0.875f,true));
  note.add(new Note(17500,1,true));
  note.add(new Note(17750,0,false));
  note.add(new Note(18000,1,false));
  note.add(new Note(18250,0,true));
}
public void mouse_set(){
  
  l=dist(mX,mY,pmX,pmY);
  dX=mX-pmX;
  dY=mY-pmY;
  
}
class Note{
  
  float direction,t;
  int x,y;
  boolean click;
  
  Note(float a, float b, boolean c){
    t=a+start;
    direction=PI*b;
    click=c;
    y=height/2;
  }
  
  public boolean check(){
    if(l==0) return false;
    //else if(abs((dY)*x-(dX)*y+mX*pmY-pmX*mY)/l>height/12) return false;
    else if(abs(t-millis())>130) return false;
    else if((dX)*cos(direction)+(dY)*sin(direction)<0.9f*l) return false;
    else if(((pmX-x)*(dX)+(pmY-y)*(dY))*((mX-x)*(dX)+(mY-y)*(dY))>0) return false;
    else if(mousePressed!=click) return false;
    return true;
  }
  
  public void display(){
    x = PApplet.parseInt( height/3 + (t-millis())*height*10/9/1500 );
    translate(x,y);
    rotate(direction);
    if(!click){
      image(note1,0,0,height/6,height/6);
    }
    else{
      image(note2,0,0,height/6,height/6);
    }
    rotate(-direction);
    translate(-x,-y);
  }
}
  public void settings() {  fullScreen(P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "barami_mcrg" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
