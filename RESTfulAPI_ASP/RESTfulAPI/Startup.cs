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
            //���� ������ �����ϱ� ���� �����͸� �������� ���� ��� HTTP 406 Not Acceptable ������ ��ȯ�Ǵ��� ���θ� �����ϴ� �÷��׸� ���� ���ų� �����մϴ�.
            //�⺻�� false
            //AddXmlDataContractSerializerFormatters
            //xml ������ �߰�
            services.AddControllers(setupAction =>
            {
                setupAction.ReturnHttpNotAcceptable = true;
            }).AddXmlDataContractSerializerFormatters();

            //AutoMapper�̿�
            services.AddAutoMapper(AppDomain.CurrentDomain.GetAssemblies());
            //java���� Autowide ���� ��??(���Ӽ� ����)
            //appsettings.json�� ConnectionString -> DefaultConnection�� ����
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
