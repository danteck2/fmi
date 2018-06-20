function [ M ] = InvertSquareMatrix( B )
   d = 1 / Determinant(B);
   T = B';
   s = size(B);
   m = s(1,1);
   
   for i = 1 : m
      for j = 1 : m
        Tc = T;
        Tc(i,:) = [];
        Tc(:,j) = [];
        dd = Determinant(Tc);
        tmp(i, j) = (-1)^(i + j) * d * dd;
      end
   end
   
   M = tmp;
   
end

