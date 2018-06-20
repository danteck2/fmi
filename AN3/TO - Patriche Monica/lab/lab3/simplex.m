function [] = simplex( A, c, b, B, B_rond )

[~, n] = size(A);
% pas 0
%x_bar=0;
x=0;
while 1
y = B\A;
x_bar = B\b;
w = c(B_rond)'*x_bar;
z = zeros(n,1);
for j = 1:n
    z(j)=c(B_rond)'*y(:, j);
end
%pas 1
optim = 1;
for j = 1:n
    if z(j) - c(j) > 0
        optim = 0;
        break;
    end
end
if optim == 1
    disp('Solutie optima');
    disp(x_bar);
    disp(w);
    if x == x_bar
        return
    end
    x = x_bar;
    %return
end
% pas 2
for k = 1:n
    if find(k==B_rond)
        continue
    end
    for j = 1:size(B_rond, 2)
        %if k ~= B_rond(j)
        if z(k) - c(k) > 0 && all(y(:, k) <= zeros(size(y,1),1))
            disp('Optim infinit');
            return
        end
        %end
    end
end
% pas 3 - schimbam baza
% aleg k
m = 0;
K = 0;
for k = 1:n
    if find(k==B_rond)
        continue
    end
    for j = 1:size(B_rond, 2)
        %if k ~= B_rond(j)
        m_ = z(j) - c(j);
        if m_ > m
            m = m_;
            K = j;
        end
        %end
    end
end
if K==0
    disp('Nu am gasit k');
    disp(x_bar);
    break;
end
% aleg r
g = abs(max(x_bar)/min(y(y>0)));
r = 0;
for i = 1:size(B_rond, 2)
    if y(i,K) > 0
        g_ = x_bar(i)/y(i,K);
        if g_ < g
            g = g_;
            r = i;
            break;
        end
    end
end
if r==0
    disp('Nu am gasit r');
    disp(x_bar);
    break;
end
B_rond(find(r)) = K;
end
end

