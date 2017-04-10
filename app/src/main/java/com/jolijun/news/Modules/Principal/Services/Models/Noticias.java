package com.jolijun.news.Modules.Principal.Services.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Noticias {

    private List<Noticia> noticias;

    public Noticias(List<Noticia> noticias){
        setSearch(noticias);
    }

    public List<Noticia> getNoticias() {
        return this.noticias;
    }

    public void setSearch(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public class Noticia {

        @SerializedName("id")
        private String id;

        @SerializedName("particion_id")
        private String particion_id;

        @SerializedName("titulo")
        private String titulo;

        @SerializedName("noticia")
        private String noticia;

        @SerializedName("extracto")
        private String extracto;

        @SerializedName("imagen")
        private String imagen;

        @SerializedName("activa")
        private boolean activa;

        @SerializedName("fecha_alta")
        private String fecha_alta;

        @SerializedName("fecha_cambio")
        private String fecha_cambio;

        public Noticia(String id, String particion_id, String titulo, String noticia, String extracto, String imagen, boolean activa, String fecha_alta, String fecha_cambio){

            setId(id);
            setParticion_id(particion_id);
            setTitulo(titulo);
            setNoticia(noticia);
            setExtracto(extracto);
            setImagen(imagen);
            setActiva(activa);
            setFecha_alta(fecha_alta);
            setFecha_cambio(fecha_cambio);

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParticion_id() {
            return particion_id;
        }

        public void setParticion_id(String particion_id) {
            this.particion_id = particion_id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getNoticia() {
            return noticia;
        }

        public void setNoticia(String noticia) {
            this.noticia = noticia;
        }

        public String getExtracto() {
            return extracto;
        }

        public void setExtracto(String extracto) {
            this.extracto = extracto;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public boolean getActiva() {
            return activa;
        }

        public void setActiva(boolean activa) {
            this.activa = activa;
        }

        public String getFecha_alta() {
            return fecha_alta;
        }

        public void setFecha_alta(String activafecha_alta) {
            this.fecha_alta = activafecha_alta;
        }

        public String getFecha_cambio() {
            return fecha_cambio;
        }

        public void setFecha_cambio(String fecha_cambio) {
            this.fecha_cambio = fecha_cambio;
        }
    }

}
