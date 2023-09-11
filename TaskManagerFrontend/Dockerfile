# ### STAGE 1: Build ###
FROM node:18.17.1 AS angular
WORKDIR /app

COPY package.json /app

RUN npm install
COPY . .

RUN npm run build

### STAGE 2: Run ###
FROM nginx:latest

COPY --from=angular /app/dist/task-manager-frontend/ /usr/share/nginx/html/

COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]