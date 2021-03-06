﻿using RESTfulAPI.Entities;
using RESTfulAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RESTfulAPI.Services
{
    public interface IBandAlbumRepository
    {
        IEnumerable<Album> GetAlbums(Guid bandId);
        Album GetAlbum(Guid bandId, Guid albumId);
        void AddAlbum(Guid bandId, Album album);
        void UpdateAlbum(Album album);
        void DeleteAlbum(Album album);

        IEnumerable<Band> GetBands();
        Band GetBand(Guid bandId);
        IEnumerable<Band> GetBands(IEnumerable<Guid> bandIds);
        IEnumerable<Band> GetBands(string mainGenre);
        IEnumerable<Band> GetBands(string mainGenre, string searchQuery);
        IEnumerable<Band> GetBands(BandsResourceParameters bandsResourceParameters);

        void AddAlbum(Band band);
        void UpdateAlbum(Band band);
        void DeleteAlbum(Band band);

        bool BandExisits(Guid bandId);
        bool AlbumExisits(Guid albumId);
        bool Save();

    }
}
