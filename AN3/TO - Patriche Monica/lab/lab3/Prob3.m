

%B=[1 2 3; 0 1 4; 5 2 1];
B=[3 0 1; 0 3 0; 0 0 1];

inversa=invr(B);
inversa
function rez = invr(B)
  Binv=eye(size(B,1));
  
  for i = 1:size(B,1)
    for j = 1:size(B,1)
      M = B;
      M(j,:)=[];
      M(:,i)=[];
      Binv(i,j)=(-1)^(i+j)*det(M);
    end;
  end;
  
  rez = 1/det(B)*Binv;
end