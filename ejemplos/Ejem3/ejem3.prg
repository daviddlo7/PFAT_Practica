pf2021 Ejem3(n)

decl
int i,j,k;
rational d, acum, alt, coc;
bool tocaNum, nuevo;

begin

acum:=0/1;
alt:=0/1;
coc:=1/1;

if (n>2)
then
  i:=2;
  k:=2;
  tocaNum:=true;
  nuevo:=false;
  j:=1;

  repeat
    if tocaNum
    then
      d:=j/k;
      if (denominador(d)=k)
      then nuevo:= true;
      end;
    else
      d:=k/j;
      if (denominador(d)=j)
      then nuevo:= true;
      end;
    end;
    if nuevo
    then
      i:= i+1;
      print("Racional " + int2str(i) + "= " + q2str(d) +
            "~ " + q2str.decimal(d,3) + ";");
      acum:= acum+d;
      coc:= coc / (d * d);
      if (numerador(i/2)=0)
      then alt:=alt+d;
      else alt:=alt-d;
      end;
      print("Acumulado= " + q2str(acum) + ";");
      print("alt= " + q2str(alt) + ";");
      print("coc= " + q2str(coc) + ";");
    end;
    nuevo:= false;
    j:=j+1;
    if (j=k)
    then
      j:=1;
      tocaNum:= not tocaNum;
      if tocaNum
      then k:=k+1;
      end;
    end;  
  until i=n;
end;
end;
