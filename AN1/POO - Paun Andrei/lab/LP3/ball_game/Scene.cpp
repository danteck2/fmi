#include "Scene.h"
#include "Background.h"
#include "BallObject.h"
#include "Player1Object.h"
#include "Player2Object.h"

Scene::Scene ()
{
	// Hardcoded construction of a new BallObject in the scene constructor
	// TODO: This need to be deleted (even as an example)
	_objects.push_back(new Background());
	_objects.push_back (new BallObject ());
	_objects.push_back(new Player1Object());
	_objects.push_back(new Player2Object());
	// _objects.push_back (new BallObject ());
	// _objects.push_back (new BallObject ());
	// _objects.push_back (new BallObject ());	
	// _objects.push_back (new BallObject ());
	// _objects.push_back (new BallObject ());
}

Scene::~Scene ()
{
	Clear ();
}

void Scene::Update ()
{
	Collision(_objects[1], _objects[2]);
	Collision(_objects[1], _objects[3]);
	for (auto it : _objects) {
		it->Update ();
	}
}

void Scene::Display ()
{
	for (auto it : _objects) {
		it->Draw ();
	}
}

void Scene::Collision(SceneObject* b, SceneObject* p)
{
	int bx=0, px=0, by=0, py=0, bR=32, pR=43;
	float pdx=0, pdy=0, bdx=0, bdy=0;
	Vector2 position_b, position_p;
	position_b = b->GetPosition();
	position_p = p->GetPosition();
	bx = position_b.GetX();
	by = position_b.GetY();
	px = position_p.GetX();
	py = position_p.GetY();
	pdx = p->Getdx();
	pdy = p->Getdy();
	bdx = b->Getdx();
	bdy = b->Getdy();

	double Dx = bx - px;
	double Dy = by - py;
	double d = sqrt(Dx*Dx + Dy*Dy);      if (d == 0) d = 0.01;
	double ax = Dx / d;
	double ay = Dy / d;

	if (d < bR + pR)
	{
		double Vn1 = pdx*ax + pdy*ay;
		double Vn2 = bdx*ax + bdy*ay;
		double dt = (bR + pR - d) / (Vn1 - Vn2);

		if (dt>0.6)  dt = 0.6;
		if (dt<-0.6) dt = -0.6;


		bx -= bdx*dt;
		by -= bdy*dt;
		px -= pdx*dt;
		py -= pdy*dt;



		Dx = bx - px;
		Dy = by - py;
		d = sqrt(Dx*Dx + Dy*Dy);  if (d == 0) d = 0.01;
		ax = Dx / d;
		ay = Dy / d;


		Vn1 = pdx*ax + pdy*ay;
		Vn2 = bdx*ax + bdy*ay;

		double Vt2 = -bdx*ay + bdy*ax;

		Vn2 = Vn1 - Vn2;

		bdx = Vn2*ax - Vt2*ay;
		bdy = Vn2*ay + Vt2*ax;

		bx += bdx*dt;
		by += bdy*dt+5;
		px += pdx*dt;
		py += pdy*dt;
	}

	position_b.SetX(bx);
	position_b.SetY(by);
	position_p.SetX(px);
	position_p.SetY(py);
	b->SetPosition(position_b);
	p->SetPosition(position_p);
	b->Setdx(bdx);
	b->Setdy(bdy);
	p->Setdx(pdx);
	p->Setdy(pdy);

}

void Scene::Clear ()
{
	for (auto it : _objects) {
		delete it;
	}
}