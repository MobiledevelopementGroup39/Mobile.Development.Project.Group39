from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('auth/', include('dj_rest_auth.urls')),
    path('auth/registration/', include('dj_rest_auth.registration.urls')),
    path('accounts/', include('allauth.urls')),
    path('conversations/', include('chat.urls')),
    path('users/', include('users.urls')),
    path('videos/',include('videos.urls')),
    path('images/',include('images.urls')),
    path('api/videos/', include('videos.api_urls')),
    path('api/images/', include('images.api_urls')),
    path('api/profile/', include('users.api_urls')),
]
