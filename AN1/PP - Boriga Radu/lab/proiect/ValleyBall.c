#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>
#include <GL/glaux.h>
#include <math.h>
#include <string.h>
#pragma comment (lib, "glaux.lib")

typedef int bool;
enum {false, true};
int speed=15;//viteza jocului
int score1=0, score2=0;
int w, h;
int k=0;
AUX_RGBImageRec *MyImage;
float gravity=0.9;
float drag=0.8;
int ground=115;//inaltimea
char gameover[15]="\0";
char difficulty[10]="\0";

struct PLAYER{
	int x, y, R;
	bool Right, Left, Up;
	float dx, dy;
	AUX_RGBImageRec *image;
};
struct PLAYER pl1;
struct PLAYER pl2;

void moveplayer1(void)
{
	pl1.R=pl1.image->sizeX/2-2;
	pl1.dy=pl1.dy-0.9;
	pl1.y+=(int)pl1.dy;
	if(pl1.y<ground) {pl1.y=ground; pl1.dy=0;}
	if(pl1.Up&&pl1.y==ground) {pl1.dy+=20; pl1.Up=false;}
	if(pl1.Right) pl1.dx=10;
	if(pl1.Left) pl1.dx=-10;
	if(!(pl1.Right||pl1.Left)) pl1.dx=0;//accelerarea dx=0
	pl1.x+=pl1.dx;
}
void moveplayer2(void)
{
	pl2.R=pl2.image->sizeX/2-2;
	pl2.dy=pl2.dy-0.9;
	pl2.y+=(int)pl2.dy;
	if(pl2.y<ground) {pl2.y=ground; pl2.dy=0;}
	if (pl2.Up&&pl2.y==ground) {pl2.dy+=20; pl2.Up=false;}
	if (pl2.Right) pl2.dx=10;
	if (pl2.Left) pl2.dx=-10;
	if (!(pl2.Right||pl2.Left)) pl2.dx=0;//accelerarea dx=0
	pl2.x+=pl2.dx;
}
void Drawplayer1(void)
{
	glEnable(GL_ALPHA_TEST);
	glAlphaFunc(GL_NOTEQUAL, 0);
	glRasterPos2d(pl1.x-pl1.R, pl1.y-pl1.R);
	glDrawPixels(pl1.image->sizeX, pl1.image->sizeY, GL_RGBA, GL_UNSIGNED_BYTE, pl1.image->data);
	glDisable(GL_ALPHA_TEST);
}
void Drawplayer2(void)
{
	glEnable(GL_ALPHA_TEST);
	glAlphaFunc(GL_NOTEQUAL, 0);
	glRasterPos2d(pl2.x-pl2.R, pl2.y-pl2.R);
	glDrawPixels(pl2.image->sizeX, pl2.image->sizeY, GL_RGBA, GL_UNSIGNED_BYTE, pl2.image->data);
	glDisable(GL_ALPHA_TEST);
}
struct BALL{
	int x, y, R;
	int oldX, oldY;//coordonatele vechi a lui x si y
	float dx, dy;
	AUX_RGBImageRec *image;
};
struct BALL ball;

void moveball(void)
{
	ball.R=ball.image->sizeX/2;
	ball.oldX=ball.x;
	ball.oldY=ball.y;
	ball.dy=ball.dy-0.9;
	ball.x+=(int)ball.dx;
	ball.y+=(int)ball.dy;
	if(ball.y<ground)
	{	
		ball.y=ground; ball.dy=-ball.dy*drag; ball.dx=ball.dx*drag;
		if(ball.x>w/2) score1++;
		if(ball.x<w/2) score2++;
		if(score1 >= 100)
		{
			score1=score2=99;
			strcpy(gameover, "Player 1 WIN \0");
			ball.x=400; ball.y=400;
			glutSetWindowTitle("ValleyBall: Player 1 WIN");
		}
		if(score2>=100)
		{
			score1=score2=99;
			strcpy(gameover, "Player 1 WIN \0");
			ball.x=400; ball.y=400;
			glutSetWindowTitle("ValleyBall: Player 2 WIN");
		}
	}
	if(ball.x<ball.R+3){ball.x=ball.R+3; ball.dx=-ball.dx;}
	if(ball.x>w-ball.R){ball.x=w-ball.R; ball.dx=-ball.dx;}
	//plasa de volei
	if((abs(w/2-ball.x))<30)//sus
		if((ball.y-ball.R<h/2)&&(ball.oldY>h/2)) {ball.y=h/2+ball.R; ball.dy=-ball.dy;}
	if(ball.y<h/2)//dreapta-stanga
	{
		if((ball.oldX<w/2)&&(ball.x+ball.R>w/2)) {ball.x=w/2-ball.R-5; ball.dx=-ball.dx;}//daca mingea a fost in stanga(oldX), iar mingea in dreapta
		if((ball.oldX>w/2)&&(ball.x-ball.R<w/2)) {ball.x=w/2+ball.R+5; ball.dx=-ball.dx;}//fata de plasa, atunci lovitura a fost din dreapta si invers
	}
	if(ball.dy>22) ball.dy=22; if(ball.dx>22) ball.dx=22;
	if(ball.dy<-22) ball.dy=-22; if(ball.dx<-22) ball.dx=-22;//limitarea de viteza
	if(ball.x==200&&ball.y==250) {ball.dx=ball.dy=0;}
	if(ball.x==400&&ball.y==400) {ball.dx=ball.dy=0;}
}
void Drawball(void)
{
	glEnable(GL_ALPHA_TEST);
	glAlphaFunc(GL_NOTEQUAL, 0);
	glRasterPos2d(ball.x-ball.R, ball.y-ball.R);
	glDrawPixels(ball.image->sizeX, ball.image->sizeY, GL_RGBA, GL_UNSIGNED_BYTE, ball.image->data);
	glDisable(GL_ALPHA_TEST);
}
void DrawField(void)
{
	glRasterPos2d(0, 0);
	glDrawPixels(MyImage->sizeX, MyImage->sizeY, GL_RGB, GL_UNSIGNED_BYTE, MyImage->data);
}
void Collision(struct PLAYER p)
{
	double d, ax, ay, Vn1, Vn2, dt, Vt2;
	double Dx=ball.x-p.x;
	double Dy=ball.y-p.y;
	d=sqrt(Dx*Dx+Dy*Dy);
	if(d==0) d=0.01;//evitarea impartirii la 0
	ax=Dx/d;
	ay=Dy/d;
	if(d<ball.R+p.R)
	{
		Vn1=p.dx*ax+p.dy*ay;
		Vn2=ball.dx*ax+ball.dy*ay;
		dt=(ball.R+p.R-d)/(Vn1-Vn2);
		if(dt>0.6) dt=0.6;
		if(dt<-0.6) dt=-0.6;
		ball.x-=ball.dx*dt;  
		ball.y-=ball.dy*dt;
		p.x-=p.dx*dt;
		p.y-=p.dy*dt;
		//gasirea vitezei noi dupa coliziune
		Dx=ball.x-p.x;
		Dy=ball.y-p.y;
		d=sqrt(Dx*Dx+Dy*Dy);
		if(d==0)d=0.01;
		ax=Dx/d;
		ay=Dy/d;
		Vn1=p.dx*ax+p.dy*ay;
		Vn2=ball.dx*ax+ball.dy*ay;
		Vt2=-ball.dx*ay+ball.dy*ax;
		Vn2=Vn1-Vn2;
		ball.dx=Vn2*ax-Vt2*ay;
		ball.dy=Vn2*ay+Vt2*ax;
		ball.x+=ball.dx*dt;
		ball.y+=ball.dy*dt;
		p.x+=p.dx*dt;
		p.y+=p.dy*dt;
	}
}
void Tick(void)
{
	moveball();
	moveplayer1();
	moveplayer2();
	Collision(pl1);
	Collision(pl2);
	//limitarea iesirii inafara ferestrei
	if(pl1.x-pl1.R<0) pl1.x=pl1.R;
	if(pl1.x+pl1.R>w/2) pl1.x=w/2-pl1.R;
	if(pl2.x+pl2.R>w) pl2.x=w-pl2.R;
	if(pl2.x-pl2.R<w/2) pl2.x=w/2+pl2.R;
}
void MouseMove(int ax, int ay)
{
	//glutSetWindowTitle("Mouse Move");
}
void MousePressed(int button, int state, int x, int y)
{
	//glutSetWindowTitle("Mouse pressed");
}
void MousePressedMove(int ax, int ay)
{
	//glutSetWindowTitle("Mouse pressedmove");
}
void MyKeyboard(unsigned char key, int a, int b)
{
	switch(key)
	{
		case 'w': case 246: pl1.Up=true; break;
		case 'd': case 226: pl1.Right=true; break;
		case 'a': case 244: pl1.Left=true; break;
		case 'i': case 248: pl2.Up=true; break;
		case 'l': case 228: pl2.Right=true; break;
		case 'j': case 238: pl2.Left=true; break;
		case 'z': case 255: speed--; break;
		case 'x': case 247: speed++; break;
	}
}
void MyUpKeyboard(unsigned char key, int a, int b)
{
	switch(key)
	{
		case 'd':case 226: pl1.Right=false; break;
		case 'a':case 244: pl1.Left=false; break;
		case 'l':case 228: pl2.Right=false; break;
		case 'j':case 238: pl2.Left=false; break;
	}
}
void TextOut2(int x, int y, char *string)//functie afisare sir de caractere
{
	char *c; glRasterPos2f(x, y);
	for(c=string; *c!='\0'; c++)
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_24, *c);
}
void TextOut3(int x, int y, char *string)
{
	char *c; glRasterPos2f(x, y);
	glPushMatrix();
	glTranslatef(200, 470, 0);
	glScalef(0.5f, 0.5f, 1.0f);
	for(c=string; *c!='\0'; c++)
		glutStrokeCharacter(GLUT_STROKE_ROMAN, *c);
	glPopMatrix();
}
void SetAlpha(AUX_RGBImageRec *image)//setarea canalul alfa transparent RGBA
{
	int i;
	int w=image->sizeX;
	int h=image->sizeY;
	unsigned char *m_pbits=(unsigned char*)malloc(4*w*h*sizeof(*m_pbits));//alocare
	for(i=0; i<w*h; i++)
	{
		m_pbits[4*i+0]=image->data[3*i+0];
		m_pbits[4*i+1]=image->data[3*i+1];
		m_pbits[4*i+2]=image->data[3*i+2];
		if((image->data[3*i+0]==0)&&(image->data[3*i+1]==0)&&(image->data[3*i+2]==0))
			m_pbits[4*i+3]=0;
		else m_pbits[4*i+3]=255;
	}
	image->data=m_pbits;
}
void display(void)
{
	char str[10], str1[10], str2[10];
	glClear(GL_COLOR_BUFFER_BIT);
	DrawField();
	Drawball();
	Drawplayer1();
	Drawplayer2();
	itoa(speed, str, 10);
	itoa(score2, str1, 10);
	itoa(score1, str2, 10);
	TextOut2(w-445, h-25, difficulty);
	TextOut2(w-410, h-50, str);
	TextOut2(w-85, h-25, "Player 2 \0");
	TextOut2(w-85, h-50, str1);
	TextOut2(w-785, h-25, "Player 1 \0");
	TextOut2(w-785, h-50, str2);
	//TextOut(w-85, h-570, "Exit \0");
	TextOut3(w-5, h-25, gameover);
	glFlush();
}
void timer(void)
{
	Tick();
	k++;
	if(k%2==0) display();//afisam fiecare al doilea cadru
	glutTimerFunc(speed, timer, 0);
}
void MenuCheck(int v)
{
	int i=0;
	switch(v)
	{
		case 0: {glutSetWindowTitle("ValleyBall: The game is started!"); break;}
		case 1: {	
					ball.dx=0;
					ball.dy=0;
					ball.x=200;
					ball.y=250;
					pl1.x=100; pl2.x=690;
					pl1.y=140; pl2.y=140;
					score1=0; score2=0;
					strcpy(gameover, " \0");
					glutSetWindowTitle("ValleyBall: The game is started!");
					break;
				}
		case 2: { 
					glutDestroyWindow(0);
					exit (0);
					glutSetWindowTitle("ValleyBall: Exit");
					break;
				}
	}
}
void SubMenuCheck(int v)
{
	switch(v)
	{
		case 3: {speed=20; strcpy(difficulty, "   EASY\0"); glutSetWindowTitle("ValleyBall: Easy"); break;}
		case 4: {speed=15; strcpy(difficulty, "MEDIUM\0"); glutSetWindowTitle("ValleyBall: Medium"); break;}
		case 5: {speed=10; strcpy(difficulty, "   HARD\0"); glutSetWindowTitle("ValleyBall: Hard"); break;}
	}
}
void MenuInit(void)
{
	int M=glutCreateMenu(MenuCheck);
	int Msub=glutCreateMenu(SubMenuCheck);
	glutSetMenu(Msub);
	glutAddMenuEntry("Easy", 3);
	glutAddMenuEntry("Medium", 4);
	glutAddMenuEntry("Hard", 5);
	glutSetMenu(M);
	glutAddMenuEntry("Start", 0);
	glutAddMenuEntry("Restart", 1);
	glutAddSubMenu("Difficulty", Msub);
	glutAddMenuEntry("Exit", 2);
	glutAttachMenu(GLUT_RIGHT_BUTTON);
	//glutAttachMenu(GLUT_LEFT_BUTTON);
}
int main(int argc, char **argv)
{
	ball.dx=0; ball.dy=0;
	ball.x=200; ball.y=250;
	pl1.x=100; pl2.x=690;
	pl1.y=140; pl2.y=140;
	pl1.image=auxDIBImageLoad("Smiley1.bmp");
	pl2.image=auxDIBImageLoad("Smiley2.bmp");
	ball.image=auxDIBImageLoad ("ball08.bmp");
	SetAlpha(ball.image);
	SetAlpha(pl1.image);
	SetAlpha(pl2.image);
	MyImage=auxDIBImageLoad("strand2.bmp");
	w=MyImage->sizeX;
	h=MyImage->sizeY;
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
	glutInitWindowSize(w, h);
	glutCreateWindow("ValleyBall: Please start the game.");
	glMatrixMode(GL_PROJECTION);
	glClearColor(0.0, 0.0, 1.0, 1.0);
	glLoadIdentity();
	gluOrtho2D(0, w, 0, h);
	glutDisplayFunc(display);
	glutMotionFunc(MousePressedMove);
	glutPassiveMotionFunc(MouseMove);
	glutMouseFunc(MousePressed);
	glutTimerFunc(50, timer, 0);
	MenuInit();
	glutKeyboardFunc(MyKeyboard);
	glutKeyboardUpFunc(MyUpKeyboard);
	glutMainLoop();
}