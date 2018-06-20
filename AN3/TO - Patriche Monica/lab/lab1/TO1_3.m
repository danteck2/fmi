f1 = inline('x','x')
f2 = inline('-2*x','x')
f3 = inline('(x-4)/(2)','x')
A=-5; B=5; 
x1=linspace(A,B,100);
y1=f1(x1);
plot(x1,y1,'y','Linewidth',3) %Constructia graficului  functiei f

grid on
hold on

x2=linspace(A,B,100);
y2=f2(x2);
plot(x2,y2,'y','Linewidth',3) %Constructia graficului  functiei f

x3=linspace(A,B,100);
y3=f3(x3);
plot(x3,y3,'y','Linewidth',3) %Constructia graficului  functiei f


plot([A B], [0 0],'k','Linewidth',2.4) %Reprezentarea axei Ox
plot([0 0], [A B],'k','Linewidth',2.4); %Reprezentarea axei Oy

[xi,yi] = polyxpoly(x1,y1,x2,y2);
[xj,yj] = polyxpoly(x3,y3,x2,y2);
[xk,yk] = polyxpoly(x3,y3,x1,y1);
mapshow(xi,yi,'DisplayType' ,'point','Marker','o')
mapshow(xj,yj,'DisplayType','point','Marker','o')
mapshow(xk,yk,'DisplayType','point','Marker','o')


patch([xi xj xk],[yi yj yk],0.2);

xlabel('x')
ylabel('y')
