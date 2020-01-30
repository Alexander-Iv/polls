FROM postgres:12
#RUN echo "listen_addresses='*'" >> /etc/postgresql/12/pollsdb/postgresql.conf
#EXPOSE 5432
COPY ./init.sql /docker-entrypoint-initdb.d/init.sql
