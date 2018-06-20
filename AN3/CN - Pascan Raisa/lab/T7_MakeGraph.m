function [] = MakeGraph(f,a, b, n)

  x = [a : 0.01 : b];
  
  yf = zeros(1, length(x));
 
  yp = zeros(1,length(x));
  
  
  for i = 1 : length(x)
  
    yf(i) = f(x(i));
    yp(i) = MetLagrange(f, a, b, n, x(i));
    
  end;
  
  err = abs(yf - yp);
 
  hold on;
  plot(x, yf, 'b');
  plot(x, yp, 'r');
  %plot(x, err, 'g');

end