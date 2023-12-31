PGDMP     /                     {            KaraokeSongPhung    15.2    15.2 &    0           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            1           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            2           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            3           1262    55194    KaraokeSongPhung    DATABASE     �   CREATE DATABASE "KaraokeSongPhung" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Vietnamese_Vietnam.1258';
 "   DROP DATABASE "KaraokeSongPhung";
                postgres    false            �            1259    55269    group_products    TABLE     {   CREATE TABLE public.group_products (
    code character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);
 "   DROP TABLE public.group_products;
       public         heap    postgres    false            �            1259    55276    order_products    TABLE     �   CREATE TABLE public.order_products (
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL
);
 "   DROP TABLE public.order_products;
       public         heap    postgres    false            �            1259    55286    orders    TABLE     M   CREATE TABLE public.orders (
    id integer NOT NULL,
    room_id integer
);
    DROP TABLE public.orders;
       public         heap    postgres    false            �            1259    55285    orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    217            4           0    0    orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
          public          postgres    false    216            �            1259    55295    payments    TABLE     
  CREATE TABLE public.payments (
    id integer NOT NULL,
    order_id integer,
    singing_time bigint DEFAULT 0 NOT NULL,
    total_money bigint DEFAULT 0 NOT NULL,
    total_money_hour bigint DEFAULT 0 NOT NULL,
    total_money_product bigint DEFAULT 0 NOT NULL
);
    DROP TABLE public.payments;
       public         heap    postgres    false            �            1259    55294    payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public.payments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.payments_id_seq;
       public          postgres    false    219            5           0    0    payments_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.payments_id_seq OWNED BY public.payments.id;
          public          postgres    false    218            �            1259    55308    products    TABLE     �   CREATE TABLE public.products (
    id integer NOT NULL,
    price real NOT NULL,
    code character varying(255) NOT NULL,
    group_product_id character varying(255),
    name character varying(255) NOT NULL
);
    DROP TABLE public.products;
       public         heap    postgres    false            �            1259    55307    products_id_seq    SEQUENCE     �   CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    221            6           0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
          public          postgres    false    220            �            1259    55319    rooms    TABLE        CREATE TABLE public.rooms (
    floor integer NOT NULL,
    id integer NOT NULL,
    is_available bit(1) DEFAULT '0'::"bit",
    ended_at timestamp(6) without time zone,
    price bigint NOT NULL,
    started_at timestamp(6) without time zone,
    name character varying(255) NOT NULL
);
    DROP TABLE public.rooms;
       public         heap    postgres    false            �            1259    55318    rooms_id_seq    SEQUENCE     �   CREATE SEQUENCE public.rooms_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.rooms_id_seq;
       public          postgres    false    223            7           0    0    rooms_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.rooms_id_seq OWNED BY public.rooms.id;
          public          postgres    false    222            |           2604    55289 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            }           2604    55298    payments id    DEFAULT     j   ALTER TABLE ONLY public.payments ALTER COLUMN id SET DEFAULT nextval('public.payments_id_seq'::regclass);
 :   ALTER TABLE public.payments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    55311    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    55322    rooms id    DEFAULT     d   ALTER TABLE ONLY public.rooms ALTER COLUMN id SET DEFAULT nextval('public.rooms_id_seq'::regclass);
 7   ALTER TABLE public.rooms ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2606    55275 "   group_products group_products_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.group_products
    ADD CONSTRAINT group_products_pkey PRIMARY KEY (code);
 L   ALTER TABLE ONLY public.group_products DROP CONSTRAINT group_products_pkey;
       public            postgres    false    214            �           2606    55282 *   order_products order_products_order_id_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.order_products
    ADD CONSTRAINT order_products_order_id_key UNIQUE (order_id);
 T   ALTER TABLE ONLY public.order_products DROP CONSTRAINT order_products_order_id_key;
       public            postgres    false    215            �           2606    55280 "   order_products order_products_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.order_products
    ADD CONSTRAINT order_products_pkey PRIMARY KEY (order_id, product_id);
 L   ALTER TABLE ONLY public.order_products DROP CONSTRAINT order_products_pkey;
       public            postgres    false    215    215            �           2606    55284 ,   order_products order_products_product_id_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.order_products
    ADD CONSTRAINT order_products_product_id_key UNIQUE (product_id);
 V   ALTER TABLE ONLY public.order_products DROP CONSTRAINT order_products_product_id_key;
       public            postgres    false    215            �           2606    55291    orders orders_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    217            �           2606    55293    orders orders_room_id_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_room_id_key UNIQUE (room_id);
 C   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_room_id_key;
       public            postgres    false    217            �           2606    55306    payments payments_order_id_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_order_id_key UNIQUE (order_id);
 H   ALTER TABLE ONLY public.payments DROP CONSTRAINT payments_order_id_key;
       public            postgres    false    219            �           2606    55304    payments payments_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.payments DROP CONSTRAINT payments_pkey;
       public            postgres    false    219            �           2606    55317 &   products products_group_product_id_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_group_product_id_key UNIQUE (group_product_id);
 P   ALTER TABLE ONLY public.products DROP CONSTRAINT products_group_product_id_key;
       public            postgres    false    221            �           2606    55315    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    221            �           2606    55325    rooms rooms_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_pkey;
       public            postgres    false    223            �           2606    55341 $   payments fk81gagumt0r8y3rmudcgpbk42l    FK CONSTRAINT     �   ALTER TABLE ONLY public.payments
    ADD CONSTRAINT fk81gagumt0r8y3rmudcgpbk42l FOREIGN KEY (order_id) REFERENCES public.orders(id);
 N   ALTER TABLE ONLY public.payments DROP CONSTRAINT fk81gagumt0r8y3rmudcgpbk42l;
       public          postgres    false    219    3214    217            �           2606    55331 *   order_products fkawxpt1ns1sr7al76nvjkv21of    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_products
    ADD CONSTRAINT fkawxpt1ns1sr7al76nvjkv21of FOREIGN KEY (order_id) REFERENCES public.orders(id);
 T   ALTER TABLE ONLY public.order_products DROP CONSTRAINT fkawxpt1ns1sr7al76nvjkv21of;
       public          postgres    false    217    3214    215            �           2606    55326 *   order_products fkdxjduvg7991r4qja26fsckxv8    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_products
    ADD CONSTRAINT fkdxjduvg7991r4qja26fsckxv8 FOREIGN KEY (product_id) REFERENCES public.products(id);
 T   ALTER TABLE ONLY public.order_products DROP CONSTRAINT fkdxjduvg7991r4qja26fsckxv8;
       public          postgres    false    215    221    3224            �           2606    55346 $   products fkkom2lg6bvj2hjy9p37588561p    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkkom2lg6bvj2hjy9p37588561p FOREIGN KEY (group_product_id) REFERENCES public.group_products(code);
 N   ALTER TABLE ONLY public.products DROP CONSTRAINT fkkom2lg6bvj2hjy9p37588561p;
       public          postgres    false    3206    221    214            �           2606    55336 "   orders fkmvji5dgxi79luuluamunmw73h    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkmvji5dgxi79luuluamunmw73h FOREIGN KEY (room_id) REFERENCES public.rooms(id);
 L   ALTER TABLE ONLY public.orders DROP CONSTRAINT fkmvji5dgxi79luuluamunmw73h;
       public          postgres    false    223    3226    217           