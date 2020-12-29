using Microsoft.EntityFrameworkCore;
using RESTfulAPI.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RESTfulAPI.DbContexts
{
    public class BandAlbumContext : DbContext
    {
        public  BandAlbumContext(DbContextOptions<BandAlbumContext> options) :
            base (options)
        {

        }

        public DbSet<Band> Bands { get; set; }
        public DbSet<Album> Albums { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Band>().HasData(
                new Band()
                {
                    Id = Guid.Parse("19ee0720-3599-442e-9d3f-6b9878188a9b"),
                    Name = "Metallica",
                    Founded = new DateTime(1980, 1, 1),
                    MainGenre = "Heavy Metal"
                },
                new Band()
                {
                    Id = Guid.Parse("2fd2bdef-f294-4751-9c8b-7e0e2acce138"),
                    Name = "Guns N Roes",
                    Founded = new DateTime(1985, 2, 1),
                    MainGenre = "Rock"
                },
                new Band()
                {
                    Id = Guid.Parse("e552346a-5b86-4482-a2f4-16899834ea2d"),
                    Name = "ABBA",
                    Founded = new DateTime(1965, 7, 1),
                    MainGenre = "Disco"
                },
                new Band()
                {
                    Id = Guid.Parse("0c252c53-7ba2-4b7b-af80-95f55191268c"),
                    Name = "Oasis",
                    Founded = new DateTime(1991, 12, 1),
                    MainGenre = "Alternative"
                },
                new Band()
                {
                    Id = Guid.Parse("d6aef8ec-2444-480c-84bd-376fe44efb26"),
                    Name = "A-ha",
                    Founded = new DateTime(1981, 6, 1),
                    MainGenre = "Pop"
                }
            );

            modelBuilder.Entity<Album>().HasData(
                new Album
                {
                    Id = Guid.Parse("044accd2-b067-4c70-a6fe-cf6765cd2a70"),
                    Title = "Master Of Puppets",
                    Description = "One of the best heavy metal albums ever",
                    BandId = Guid.Parse("19ee0720-3599-442e-9d3f-6b9878188a9b")
                },
                new Album
                {
                    Id = Guid.Parse("d71898ae-2480-42a7-8e4b-fd251fd84a39"),
                    Title = "Appetite for Destruction",
                    Description = "Amazing Rock album with raw sound",
                    BandId = Guid.Parse("2fd2bdef-f294-4751-9c8b-7e0e2acce138")
                },
                new Album
                {
                    Id = Guid.Parse("004f9da8-96bf-485e-9945-7997133ea5aa"),
                    Title = "Waterloo",
                    Description = "Very groovy album",
                    BandId = Guid.Parse("e552346a-5b86-4482-a2f4-16899834ea2d")
                },
                new Album
                {
                    Id = Guid.Parse("c72195d9-fcc2-470b-a5c7-414adf52bfdd"),
                    Title = "Be Here Now",
                    Description = "Arguably one of the best albums by Oasis",
                    BandId = Guid.Parse("0c252c53-7ba2-4b7b-af80-95f55191268c")
                },
                new Album
                {
                    Id = Guid.Parse("6e16275b-acdf-45e4-9f4a-3229bfdea9d4"),
                    Title = "Hunting Hight and Low",
                    Description = "Awesome Debut album by A-Ha",
                    BandId = Guid.Parse("d6aef8ec-2444-480c-84bd-376fe44efb26")
                }
            );

            base.OnModelCreating(modelBuilder);
        }
    }
}
