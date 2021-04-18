pf2021 NumE(n)

decl
int i;
rational e, term;

begin

i:=1;
e:=1/1;
term:=1/1;

if (n>0)
then
  repeat
    term:=term/i;
    i:= i+1;
    e:= e+term;
  until i>n;
  print("e~ " + q2str(e));
end;
end;
  




