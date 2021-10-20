create schema if not exists metflix;

use metflix;

create table director(
	id int unsigned auto_increment,
    `name` varchar(35),
    nationality varchar(40),
    constraint pk_director_id primary key(id)
);

create table movie(
	id int unsigned auto_increment,
    title varchar(60),
	nationality varchar(40),
    production_company varchar(35),
    year_release smallint  unsigned, 
    id_director int unsigned,
    budget bigint unsigned,
	box_office bigint unsigned,
    running_time smallint unsigned,
	constraint pk_movie_id primary key(id),
	constraint fk_director_id_director foreign key(id_director)
		references director (id)
			on delete cascade
            on update cascade
);
