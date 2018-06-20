function [] = myPlot()
a = -1;
b = 1;
n = 4;
x = 1;
step = 0.01;
f = @(x)exp(2*x);
fd = @(x)2*exp(2*x);
noduri = 10 * (n + 1);
% noduri = n;

puncte = zeros(1,noduri+1);
puncte(1) = a;
h = (b - a) / noduri;
for i = 2:noduri + 1
      puncte(i) = puncte(i - 1) + h;
end;

valori = f(puncte);

caz = 3;

pctFct = f(a : step : b);
pctSpline = zeros(1, size(a : step : b,1));
switch caz
    case 1

        i = 1;
        for j = a : step : b
            pctSpline(i) = SplineLin(f, a, b, noduri, j);
            i = i + 1;
        end

        plot(a : step : b, pctFct, 'r');
        hold on;
        plot(a : step : b, pctSpline, 'b');
        scatter(puncte, valori);
        hold off;
    case 2
        i = 1;
        for j = a : step : b
            pctSpline(i) = SplinePatratic(f, fd, a, b, noduri, j);
            i = i + 1;
        end

        plot(a : step : b, pctFct, 'r');
        hold on;
        plot(a : step : b, pctSpline,'b');
        scatter(puncte, valori);
        hold off;
    case 3
        i = 1;
        for j = a : step : b
            pctSpline(i) = SplineCubic(f, fd, a, b, noduri, j);
            i = i + 1;
        end

        plot(a : step : b, pctFct, 'r');
        hold on;
        plot(a : step : b, pctSpline,'b');
        scatter(puncte, valori);
        hold off;
end
end

