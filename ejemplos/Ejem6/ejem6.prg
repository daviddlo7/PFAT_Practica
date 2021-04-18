pf2021 Pruebas

decl
int i;
rational e, term;
bool b;

begin

i:=1;
e:=1/1;
term:=1/1;

repeat
  i:=i+1;
  e:=-e/-i;
until (i/9999)=(17/9999);
print("Resultado 1= " + q2str(e));

e:=1/1;
i:= 25;

repeat
  i:=i-1;
  e:=-e/-i;
until (i/9999)=(17/9999);
print("Resultado 2= " + q2str(e));
print("Resultado 3= " + int2str(parte.entera(e)));
print("Resultado 4= " + int2str(parte.entera(-1/20)));
print("Resultado 5= " + int2str(parte.entera(-5/2)));
print("Resultado 6= " + int2str(parte.entera(1/20)));

b:= (2 = 46/23);
if (b)
then print("Resultado 7 correcto");
end;

b:=  (23/46) < 2;
if (b)
then print("Resultado 8 correcto");
end;

b:= true and b;
if (b)
then print("Resultado 9 correcto");
end;

b:= false or (not b);
if (not b)
then print("Resultado 10 correcto");
end;







end;
  




