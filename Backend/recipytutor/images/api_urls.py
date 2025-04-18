from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .api_views import ImagePostViewSet, ImageCommentViewSet

router = DefaultRouter()
router.register(r'upload_images', ImagePostViewSet, basename='image')
router.register(r'image-comments', ImageCommentViewSet, basename='image-comment')

urlpatterns = [
    path('', include(router.urls)),
]
