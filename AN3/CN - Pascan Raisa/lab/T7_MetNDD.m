function [ rez ] = MetNDD(f, a, b, n, x)

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
  
  Q = zeros(n + 1);
  for i = 1 : n + 1
    Q(i, 1) = y(i);
  end;
  
  for j = 2 : n + 1
      for i = j : n + 1
          Q(i,j) = (Q(i, j - 1) - Q(i - 1, j - 1)) / (puncte(i) - puncte(i - j + 1));
      end;
  end;
  
  rez = Q(1,1);
  
  for k = 2 : n + 1
    product = Q(k,k);
    for i = 1 : k - 1
      product = product * (x - puncte(i));
     end;
     rez = rez + product;
  end;
end


