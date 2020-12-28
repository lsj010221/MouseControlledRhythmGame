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
  
  boolean check(){
    if(l==0) return false;
    //else if(abs((dY)*x-(dX)*y+mX*pmY-pmX*mY)/l>height/12) return false;
    else if(abs(t-millis())>130) return false;
    else if((dX)*cos(direction)+(dY)*sin(direction)<0.9*l) return false;
    else if(((pmX-x)*(dX)+(pmY-y)*(dY))*((mX-x)*(dX)+(mY-y)*(dY))>0) return false;
    else if(mousePressed!=click) return false;
    return true;
  }
  
  void display(){
    x = int( height/3 + (t-millis())*height*10/9/1500 );
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
