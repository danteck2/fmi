#include <iostream>
#include <time.h>
#include <cstdlib>
#include <GL/glut.h>

using namespace std;

struct Punct{
float x,y;};
float mod(int a)
{
    if( a>=0)
        return a;
    return -a;
}

int n,i,j,k,nr=0,nrv=0;
float v[100];
Punct a[100],M;

float  arie(Punct a, Punct b, Punct c)
    {
        float x=0.5 * mod((a.x*b.y + b.x*c.y + a.y*c.x - b.y*c.x - a.x*c.y - a.y*b.x));
        return x;
    }
bool  inter(Punct A, Punct B, Punct C, Punct M)
    {
        if (arie(M,A,B) + arie(M, B, C) + arie(M,A,C)  ==  arie(A,B,C))
            return true;
        return false;
    }
int coliniar(Punct A, Punct B, Punct M)
{
    if(!((A.x<=M.x && M.x<=B.x && A.y<=M.y && M.y<=B.y) || (A.x>=M.x && M.x>=B.x && A.y>=M.y && M.y>=B.y)))
        return 0;
    if(((A.x*B.y)+(B.x*M.y)+(M.x*A.y)-(M.x*B.y)-(A.x*M.y)-(B.x*A.y))==0)
        return 1;
    return 0;
}

void display()
{
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(0.0, 1.0, 0.0);
    glBegin(GL_POLYGON);
    for(int i=0; i<n; i++)
    {
        glVertex2f(a[i].x, a[i].y);
    }
    glEnd();

    glBegin(GL_POINTS);
    glColor3f(1.0, 0.0, 0.0);
    glVertex2f(M.x, M.y);
    glVertex2f(M.x+0.3, M.y+0.3);
    glVertex2f(M.x+0.3, M.y-0.3);
    glVertex2f(M.x-0.3, M.y-0.3);
    glVertex2f(M.x-0.3, M.y+0.3);

    glVertex2f(M.x+0.3, M.y+0.6);
    glVertex2f(M.x+0.6, M.y+0.6);
    glVertex2f(M.x+0.6, M.y+0.3);

    glVertex2f(M.x+0.3, M.y-0.6);
    glVertex2f(M.x+0.6, M.y-0.6);
    glVertex2f(M.x+0.6, M.y-0.3);

    glVertex2f(M.x-0.3, M.y-0.6);
    glVertex2f(M.x-0.6, M.y-0.6);
    glVertex2f(M.x-0.6, M.y-0.3);

    glVertex2f(M.x-0.3, M.y+0.6);
    glVertex2f(M.x-0.6, M.y+0.6);
    glVertex2f(M.x-0.6, M.y+0.3);
    glEnd();

    glBegin(GL_LINES);
    glColor3f(0.0, 0.0, 1.0);
    for(int i=0; i<n-2; i++)
    {
        glVertex2f(a[i].x, a[i].y);
        glVertex2f(a[i+2].x, a[i+2].y);
    }
    glVertex2f(a[i+1].x, a[i+1].y);
    glVertex2f(a[0].x, a[0].y);
    //glVertex2f(a[i+2].x, a[i+2].y);
    //glVertex2f(a[1].x, a[1].y);
    glEnd();

    glFlush();
}

int main(int argc, char **argv)
{
    cout<<"\nCiteste numarul de puncte:";
    cin>>n; cout<<'\n';
    while(n<=2)
    {
         cout<<"\nCiteste numarul de puncte:";
         cin>>n; cout<<'\n';
    }
    for(i=0;i<n;i++)
    {
        cout<<"Coordonatele punctului "<<i+1<<" sunt:";
        cin>>a[i].x>>a[i].y;
    }
    cout<<"\nM:";
    cin>>M.x>>M.y;

    int ok=0;
    for(i=0;i<n-3;i++)
                if(inter(a[i],a[i+1],a[1+2],M))
                    ok=1;
    if(inter(a[n-2],a[n-1],a[0],M))
        ok=1;

  if(coliniar(a[n-1],a[0],M))
        ok=2;

    for(i=0;i<n-2;i++)
            if(coliniar(a[i],a[i+1],M))
                ok=2;

    if(ok==1)
        cout<<"\nPunctul se afla in interiorul acoperirii convexe a poligonului\n";
    else
        if(ok==2)
            cout<<"\nPunctul este situat pe una dintre laturi\n";
        else
            cout<<"\nPunctul este situat in exteriorul poligonului\n";


    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(600, 600);
    glutCreateWindow("Proiect Geometrie");
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-100, 100, -100, 100); //x y pozitive
    glutDisplayFunc(display);
    glutMainLoop();
}
