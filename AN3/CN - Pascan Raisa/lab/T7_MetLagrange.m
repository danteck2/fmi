function [ rez ] = MetLagrange(f, a, b, n, x)

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
  
  Ln = zeros(1, n + 1);
 
  for k = 1 : n + 1
    
    numitor = 1;
    numarator = 1;
    for i = 1 : n + 1
        if i ~= k
            numitor = numitor * (x - puncte(i));
            numarator = numarator * (puncte(k) - puncte(i));
            
        end;
        
    end;
    Ln(k) = numitor / numarator; 
  
  end;
  
   rez = 0;
   for k = 1 : n + 1
     rez = rez + Ln(k) * y(k);
   end;
 
end


