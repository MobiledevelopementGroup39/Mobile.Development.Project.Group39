from rest_framework import serializers
from .models import ImagePost, ImageComment

class ImagePostSerializer(serializers.ModelSerializer):
    total_likes = serializers.ReadOnlyField()
    total_dislikes = serializers.ReadOnlyField()

    class Meta:
        model = ImagePost
        fields = ['id', 'title', 'image', 'uploaded_by', 'uploaded_at', 'total_likes', 'total_dislikes']


class ImageCommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = ImageComment
        fields = ['id', 'image', 'user', 'text', 'created_at']
