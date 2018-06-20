function [ rez ] = MetNaiva(f, a, b, n, x)

  puncte = zeros(1, n + 1);
  puncte(1) = a;
  h = (b - a) / n;
  for i = 2 : n + 1
    puncte(i) = puncte(i - 1) + h;
  end;
  
  
  y = zeros(1, n + 1);
  for i = 1 : n + 1
    y(i) = f(puncte(i));
  end;
  
  
  A = zeros(n + 1);
  for i = 1 : n + 1
    A(:,i) = puncte.^(i - 1);
  end;
  inve = inv(A);
  a = inve * y';
   
  rez = 0;
  
  for i = 1 : length(a)
    rez = rez + a(i) * x ^ (i - 1);
    
  end;
end

