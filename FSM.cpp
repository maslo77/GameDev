#include <iostream>
#include <functional>
#include <cmath>
#include <thread>
#include <chrono>

using namespace std;
struct Vector2D
{
    float x,y;

    Vector2D(float x,float y)
    {
        this->x = x;
        this->y = y;
    }
    Vector2D& operator +=(const Vector2D& v)
    {
        this->x += v.x;
        this->y += v.y;
        return *this;
    }
};

Vector2D *user = new Vector2D(30,50);
Vector2D *home = new Vector2D(0,0);
Vector2D *leadPosition = new Vector2D(100,100);

float distance(Vector2D *v1,Vector2D *v2)
{
    return sqrt(pow(v1->x - v2->x,2) +pow(v1->y - v2->y,2));
}
class FSM{
private:
   function<void()> activateState;
public:
    FSM(){
        this->activateState = NULL;

    }
    void setState(function<void()>state){
        this->activateState = state;

    }
    void update(){
        if(this->activateState !=NULL) this->activateState();

    }
};
class Ant{
public:
    Vector2D *position;
    Vector2D *velocity;
    FSM *brain;

    Ant(float posX,float posY)
    {
        this->position = new Vector2D(posX,posY);
        this->velocity = new Vector2D(-1,1);
        this->brain = new FSM();
        this->brain->setState(bind(&Ant::findLeaf,this));
    }
    void findLeaf()
    {
        cout<<"Szukam Liscia"<< this->position->x<<" "<< this->position->y<<endl;;
        this->velocity = new Vector2D(leadPosition->x -position->x,leadPosition->y - position->y);
        if(distance(leadPosition,this->position) < 10) this->brain->setState(bind(&Ant::goHome,this));
        if(distance(user,this->position) < 10) this->brain->setState(bind(&Ant::runAway,this));
    }
    void goHome()
    {
        cout<<"Mam liscia, do domu lece"<< this->position->x<<" "<< this->position->y<<endl;;
        this->velocity = new Vector2D(leadPosition->x -position->x,leadPosition->y - position->y);
        if(distance(home,this->position) < 10) this->brain->setState(bind(&Ant::findLeaf,this));

    }
    void runAway()
    {
        cout<<"AA!! Czyjas syra!!"<<endl;
        this->velocity = new Vector2D(user->x +position->x,user->y + position->y);
        if(distance(home,this->position) > 10) this->brain->setState(bind(&Ant::findLeaf,this));
    }
    void move()
    {
        *this->position += *this->velocity;
    }
    void update(){
        brain->update();
        this->move();
    }
};

int main()
{   Ant *ant = new Ant(10,10);
    cout<< ant->position->x<<" "<< ant->position->y<<endl;
    ant->update();
    cout<< ant->position->x<<" "<< ant->position->y<<endl;
    FSM *fsm = new FSM();
    cout<<"DZIALA !"<<endl;
    cout << "Hello World!" << endl;
    return 0;
}
