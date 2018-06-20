function [ rez ] = MetNewton(f, a, b, n, x)
  
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
      A(i, 1) = 1;
      for j = 2 : n + 1
          if~(i==1)
              A(i,j) = 1;
              for k = 1 : j-1
                A(i,j) = A(i,j) * (puncte(i) - puncte(k));
              end;
          end;
      end;
  end;
  inve = inv(A);
  a = inve * y';
  
  rez = a(1);
  
  for i = 2 : length(a)
    product = a(i)
    for j = 1 : i - 1
        product = product * (x - puncte(j));
    end
    rez = rez + product
  end; 
  
end


