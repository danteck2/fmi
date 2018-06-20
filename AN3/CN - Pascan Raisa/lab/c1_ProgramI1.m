f = inline('sqrt(x)-cos(x)','x')
A=0; B=1; eps = 10^(-5);
xaprox=MetBisectie(f,A,B,eps)
x=linspace(A,B,100);
y=f(x);
plot(x,y,'Linewidth',3) %Constructia graficului  functiei

grid on
hold on
plot([A B], [0 0],'k','Linewidth',2.4) %Reprezentarea axei Ox
plot([0 0], [min(y) max(y)],'k','Linewidth',2.4); %Reprezentarea axei Oy
plot(xaprox,f(xaprox),'o','MarkerFaceColor','g','MarkerSize',10) %Reprezentarea solutiei pe grafic
xlabel('x')
ylabel('y')
title('f(x)=sqrt(x)-cos(x)')

