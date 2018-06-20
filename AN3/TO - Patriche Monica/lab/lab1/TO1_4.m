f1 = inline('x','x')
f2 = inline('2*x','x')

A=-5; B=5; 
x1=linspace(A,B,100);
y1=f1(x1);
plot(x1,y1,'y','Linewidth',3) %Constructia graficului  functiei f

grid on
hold on

x2=linspace(A,B,100);
y2=f2(x2);
plot(x2,y2,'y','Linewidth',3) %Constructia graficului  functiei f


plot([A B], [0 0],'k','Linewidth',2.4) %Reprezentarea axei Ox
plot([0 0], [A B],'k','Linewidth',2.4); %Reprezentarea axei Oy

[xi,yi] = polyxpoly(x1,y1,x2,y2);
mapshow(xi,yi,'DisplayType' ,'point','Marker','o')


patch([0 5 5],[0 10 5],0.2);

xlabel('x')
ylabel('y')
