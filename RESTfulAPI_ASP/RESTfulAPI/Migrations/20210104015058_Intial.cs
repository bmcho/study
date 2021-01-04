using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace RESTfulAPI.Migrations
{
    public partial class Intial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Bands",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Name = table.Column<string>(type: "nvarchar(100)", maxLength: 100, nullable: false),
                    Founded = table.Column<DateTime>(type: "datetime2", nullable: false),
                    MainGenre = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Bands", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Albums",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Title = table.Column<string>(type: "nvarchar(100)", maxLength: 100, nullable: false),
                    Description = table.Column<string>(type: "nvarchar(400)", maxLength: 400, nullable: true),
                    BandId = table.Column<Guid>(type: "uniqueidentifier", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Albums", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Albums_Bands_BandId",
                        column: x => x.BandId,
                        principalTable: "Bands",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "Bands",
                columns: new[] { "Id", "Founded", "MainGenre", "Name" },
                values: new object[,]
                {
                    { new Guid("19ee0720-3599-442e-9d3f-6b9878188a9b"), new DateTime(1980, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Heavy Metal", "Metallica" },
                    { new Guid("2fd2bdef-f294-4751-9c8b-7e0e2acce138"), new DateTime(1985, 2, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Rock", "Guns N Roes" },
                    { new Guid("e552346a-5b86-4482-a2f4-16899834ea2d"), new DateTime(1965, 7, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Disco", "ABBA" },
                    { new Guid("0c252c53-7ba2-4b7b-af80-95f55191268c"), new DateTime(1991, 12, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Alternative", "Oasis" },
                    { new Guid("d6aef8ec-2444-480c-84bd-376fe44efb26"), new DateTime(1981, 6, 1, 0, 0, 0, 0, DateTimeKind.Unspecified), "Pop", "A-ha" }
                });

            migrationBuilder.InsertData(
                table: "Albums",
                columns: new[] { "Id", "BandId", "Description", "Title" },
                values: new object[,]
                {
                    { new Guid("044accd2-b067-4c70-a6fe-cf6765cd2a70"), new Guid("19ee0720-3599-442e-9d3f-6b9878188a9b"), "One of the best heavy metal albums ever", "Master Of Puppets" },
                    { new Guid("d71898ae-2480-42a7-8e4b-fd251fd84a39"), new Guid("2fd2bdef-f294-4751-9c8b-7e0e2acce138"), "Amazing Rock album with raw sound", "Appetite for Destruction" },
                    { new Guid("004f9da8-96bf-485e-9945-7997133ea5aa"), new Guid("e552346a-5b86-4482-a2f4-16899834ea2d"), "Very groovy album", "Waterloo" },
                    { new Guid("c72195d9-fcc2-470b-a5c7-414adf52bfdd"), new Guid("0c252c53-7ba2-4b7b-af80-95f55191268c"), "Arguably one of the best albums by Oasis", "Be Here Now" },
                    { new Guid("6e16275b-acdf-45e4-9f4a-3229bfdea9d4"), new Guid("d6aef8ec-2444-480c-84bd-376fe44efb26"), "Awesome Debut album by A-Ha", "Hunting Hight and Low" }
                });

            migrationBuilder.CreateIndex(
                name: "IX_Albums_BandId",
                table: "Albums",
                column: "BandId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Albums");

            migrationBuilder.DropTable(
                name: "Bands");
        }
    }
}
