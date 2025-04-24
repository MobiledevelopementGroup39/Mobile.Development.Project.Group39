from django.contrib import admin
from django.urls import path, include
from django.conf import settings  # Add this
from django.conf.urls.static import static  # Add this

urlpatterns = [
    path('admin/', admin.site.urls),
    path('auth/', include('dj_rest_auth.urls')),
    path('auth/registration/', include('dj_rest_auth.registration.urls')),
    path('accounts/', include('allauth.urls')),
    path('conversations/', include('chat.urls')),
    path('users/', include('users.urls')),
    path('videos/', include('videos.urls')),
    path('images/', include('images.urls')),
    path('api/videos/', include('videos.api_urls')),
    path('api/images/', include('images.api_urls')),
    path('api/profile/', include('users.api_urls')),
]

# ======= ADD THIS PART TO HANDLE MEDIA FILES IN DEVELOPMENT ======= #
if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)