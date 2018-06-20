B = [1 0; 1 1];

Binv = InvertSquareMatrix(B);

r=2;
A = [2;5];
rez=LemaSubts(B,r,A,Binv); 
disp(rez);