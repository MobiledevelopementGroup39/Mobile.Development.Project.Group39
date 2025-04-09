from django.urls import path
from . import views

urlpatterns = [
    path('', views.video_list, name='video_list'),
    path('upload/', views.upload_video, name='upload_video'),
    path('<int:video_id>/like/', views.like_video, name='like_video'),
    path('<int:video_id>/dislike/', views.dislike_video, name='dislike_video'),
    path('<int:video_id>/comment/', views.add_video_comment, name='add_video_comment'),
]
