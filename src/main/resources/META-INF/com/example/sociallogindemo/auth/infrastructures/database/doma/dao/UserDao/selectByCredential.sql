select
  /*%expand*/*
from
  user
where
    name = /* name */"DUMMY"
  and
    password = /* password */"DUMMY"
  limit 1;
