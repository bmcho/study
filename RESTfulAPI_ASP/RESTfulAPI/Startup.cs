using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using RESTfulAPI.DbContexts;
using RESTfulAPI.Services;
using AutoMapper;

namespace RESTfulAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            //services.AddControllers();
            //응답 형식을 지정하기 위해 포맷터를 선택하지 않은 경우 HTTP 406 Not Acceptable 응답이 반환되는지 여부를 결정하는 플래그를 가져 오거나 설정합니다.
            //기본값 false
            //AddXmlDataContractSerializerFormatters
            //xml 포맷터 추가
            services.AddControllers(setupAction =>
            {
                setupAction.ReturnHttpNotAcceptable = true;
            }).AddXmlDataContractSerializerFormatters();

            //AutoMapper이용
            services.AddAutoMapper(AppDomain.CurrentDomain.GetAssemblies());
            //java에서 Autowide 같은 놈??(종속성 주입)
            //appsettings.json의 ConnectionString -> DefaultConnection의 정보
            services.AddScoped<IBandAlbumRepository, BandAlbumRepository>();
            services.AddDbContext<BandAlbumContext>(options =>
            {
                options.UseSqlServer(Configuration.GetConnectionString("DefaultConnection"));
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
