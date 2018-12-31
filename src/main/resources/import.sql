insert into post(id, title, content) values (1, '제목1', '내용1');
insert into post(id, title, content) values (2, '제목2', '내용2');

insert into comment(id, post_id, content) values (1, 1, '댓글1');
insert into comment(id, post_id, content) values (2, 1, '댓글1-1');
insert into comment(id, post_id, content) values (3, 2, '댓글2');