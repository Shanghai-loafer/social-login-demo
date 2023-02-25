select
  /*%expand*/*
from
  user
where
    name = /* name */'DUMMY'
limit 1;
