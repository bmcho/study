﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using RESTfulAPI.DbContexts;

namespace RESTfulAPI.Migrations
{
    [DbContext(typeof(BandAlbumContext))]
    [Migration("20210104015058_Intial")]
    partial class Intial
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .UseIdentityColumns()
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.1");

            modelBuilder.Entity("RESTfulAPI.Entities.Album", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uniqueidentifier");

                    b.Property<Guid>("BandId")
                        .HasColumnType("uniqueidentifier");

                    b.Property<string>("Description")
                        .HasMaxLength(400)
                        .HasColumnType("nvarchar(400)");

                    b.Property<string>("Title")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.HasKey("Id");

                    b.HasIndex("BandId");

                    b.ToTable("Albums");

                    b.HasData(
                        new
                        {
                            Id = new Guid("044accd2-b067-4c70-a6fe-cf6765cd2a70"),
                            BandId = new Guid("19ee0720-3599-442e-9d3f-6b9878188a9b"),
                            Description = "One of the best heavy metal albums ever",
                            Title = "Master Of Puppets"
                        },
                        new
                        {
                            Id = new Guid("d71898ae-2480-42a7-8e4b-fd251fd84a39"),
                            BandId = new Guid("2fd2bdef-f294-4751-9c8b-7e0e2acce138"),
                            Description = "Amazing Rock album with raw sound",
                            Title = "Appetite for Destruction"
                        },
                        new
                        {
                            Id = new Guid("004f9da8-96bf-485e-9945-7997133ea5aa"),
                            BandId = new Guid("e552346a-5b86-4482-a2f4-16899834ea2d"),
                            Description = "Very groovy album",
                            Title = "Waterloo"
                        },
                        new
                        {
                            Id = new Guid("c72195d9-fcc2-470b-a5c7-414adf52bfdd"),
                            BandId = new Guid("0c252c53-7ba2-4b7b-af80-95f55191268c"),
                            Description = "Arguably one of the best albums by Oasis",
                            Title = "Be Here Now"
                        },
                        new
                        {
                            Id = new Guid("6e16275b-acdf-45e4-9f4a-3229bfdea9d4"),
                            BandId = new Guid("d6aef8ec-2444-480c-84bd-376fe44efb26"),
                            Description = "Awesome Debut album by A-Ha",
                            Title = "Hunting Hight and Low"
                        });
                });

            modelBuilder.Entity("RESTfulAPI.Entities.Band", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uniqueidentifier");

                    b.Property<DateTime>("Founded")
                        .HasColumnType("datetime2");

                    b.Property<string>("MainGenre")
                        .IsRequired()
                        .HasMaxLength(50)
                        .HasColumnType("nvarchar(50)");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.HasKey("Id");

                    b.ToTable("Bands");

                    b.HasData(
                        new
                        {
                            Id = new Guid("19ee0720-3599-442e-9d3f-6b9878188a9b"),
                            Founded = new DateTime(1980, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified),
                            MainGenre = "Heavy Metal",
                            Name = "Metallica"
                        },
                        new
                        {
                            Id = new Guid("2fd2bdef-f294-4751-9c8b-7e0e2acce138"),
                            Founded = new DateTime(1985, 2, 1, 0, 0, 0, 0, DateTimeKind.Unspecified),
                            MainGenre = "Rock",
                            Name = "Guns N Roes"
                        },
                        new
                        {
                            Id = new Guid("e552346a-5b86-4482-a2f4-16899834ea2d"),
                            Founded = new DateTime(1965, 7, 1, 0, 0, 0, 0, DateTimeKind.Unspecified),
                            MainGenre = "Disco",
                            Name = "ABBA"
                        },
                        new
                        {
                            Id = new Guid("0c252c53-7ba2-4b7b-af80-95f55191268c"),
                            Founded = new DateTime(1991, 12, 1, 0, 0, 0, 0, DateTimeKind.Unspecified),
                            MainGenre = "Alternative",
                            Name = "Oasis"
                        },
                        new
                        {
                            Id = new Guid("d6aef8ec-2444-480c-84bd-376fe44efb26"),
                            Founded = new DateTime(1981, 6, 1, 0, 0, 0, 0, DateTimeKind.Unspecified),
                            MainGenre = "Pop",
                            Name = "A-ha"
                        });
                });

            modelBuilder.Entity("RESTfulAPI.Entities.Album", b =>
                {
                    b.HasOne("RESTfulAPI.Entities.Band", "Band")
                        .WithMany("Albums")
                        .HasForeignKey("BandId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Band");
                });

            modelBuilder.Entity("RESTfulAPI.Entities.Band", b =>
                {
                    b.Navigation("Albums");
                });
#pragma warning restore 612, 618
        }
    }
}