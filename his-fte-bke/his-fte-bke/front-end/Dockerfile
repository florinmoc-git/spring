#### Stage 1: Build the react application
FROM node:16-alpine as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . ./
RUN npm run build

#### Stage 2: Serve the React application from Nginx 
FROM nginx:1.22-alpine
WORKDIR /usr/share/nginx/html
COPY --from=build /app/build .
EXPOSE 80

ENTRYPOINT ["nginx","-g","daemon off;"]