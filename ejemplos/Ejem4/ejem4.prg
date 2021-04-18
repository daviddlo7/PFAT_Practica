pf2021 NumPI(n)

decl
int i,j;
rational pim, term;

begin

i:=1;
pim:=1/1;


if (n>0)
then
  repeat
    j:=1;
    term:=1/(2*i+1);
    repeat
      term:= term * (i+j)/(j*4);
      j:=j+1;
    until j>i;
    i:= i+1;
    pim:= pim+term;
  until i>n;
  print("pi~ " + q2str(pim*2));
end;
end;
  




